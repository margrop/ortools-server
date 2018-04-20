package me.ymq.ortools.util;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.*;

public class NativeLibLoader {
    private static final Logger logger = LoggerFactory.getLogger(NativeLibLoader.class);

    public static void load(String snk) {
        String currentOS = System.getProperty("os.name");

        if (currentOS.contains("Windows") && snk.endsWith("dll")) {
            loadFile(snk);
        } else if (currentOS.contains("Linux") && snk.endsWith("so")) {
            String linuxPrefix = linuxPrefix();
            loadFile(linuxPrefix + "-" + snk);
        } else {
            logger.info("NativeLibLoader : not supported " + currentOS);
        }
    }

    private static String linuxPrefix() {
        Runtime run = Runtime.getRuntime();
        StringBuilder sb = new StringBuilder();
        try {
            // 启动另一个进程来执行命令
            Process p = run.exec((new String[]{"cat", "/etc/*-release"}));
            BufferedInputStream in = new BufferedInputStream(p.getInputStream());
            BufferedReader inBr = new BufferedReader(new InputStreamReader(in));
            String lineStr;
            //获得命令执行后在控制台的输出信息
            while ((lineStr = inBr.readLine()) != null) {
                // 打印输出信息
                logger.info(lineStr);
                sb.append(lineStr);
            }
            //检查命令是否执行失败。
            if (p.waitFor() != 0) {
                //p.exitValue()==0表示正常结束，1：非正常结束
                if (p.exitValue() == 1) {
                    logger.error("命令执行失败!");
                }
            }
            inBr.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String releaseInfo = sb.toString();
        if (releaseInfo.contains("Debian")) {
            return "debian";
        }
        return "centos";
    }

    public static void loadFile(String snk) {
        String src = "classpath:" + snk;
        try {
            File file = copyResourceToTempDirFile(src, snk);
            String filePath = file.getAbsolutePath();
            System.load(filePath);
            logger.info("NativeLibLoader : load " + filePath + " successful");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static File copyResourceToTempDirFile(String src, String snk) throws IOException {
        File tempDir = new File(System.getProperty("java.io.tmpdir"));
        File tempDirFile = new File(tempDir, snk);

        PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = patternResolver.getResources(src);

        if (resources.length == 0) {
            return null;
        }

        try (InputStream input = resources[0].getInputStream();
             OutputStream output = new FileOutputStream(tempDirFile)) {
            IOUtils.copy(input, output);
            tempDirFile.deleteOnExit();
            return tempDirFile;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
