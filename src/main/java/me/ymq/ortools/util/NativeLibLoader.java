package me.ymq.ortools.util;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.*;

public class NativeLibLoader {
    private static final Logger logger = LoggerFactory.getLogger(NativeLibLoader.class);

    public static void load(String src, String snk) {
        try {
            File file = copyResourceToTempDirFile(src, snk);
            System.load(file.getAbsolutePath());
            logger.info("NativeLibLoader : load " + snk + " successful");

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
