package me.ymq.ortools;

import me.ymq.ortools.util.NativeLibLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class OrtoolsApplication extends SpringBootServletInitializer {
    static {
        NativeLibLoader.load("classpath:jniortools.dll", "jniortools.dll");
        NativeLibLoader.load("classpath:libfap.so", "libfap.so");
        NativeLibLoader.load("classpath:libdimacs.so", "libdimacs.so");
        NativeLibLoader.load("classpath:libcvrptw_lib.so", "libcvrptw_lib.so");
        NativeLibLoader.load("classpath:libortools.so", "libortools.so");
        NativeLibLoader.load("classpath:libjniortools.so", "libjniortools.so");
    }

    public static void main(String[] args) {
        SpringApplication.run(OrtoolsApplication.class, args);
    }
}
