package me.ymq.ortools;

import me.ymq.ortools.util.NativeLibLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class OrtoolsApplication extends SpringBootServletInitializer {
    static {
        NativeLibLoader.load("classpath:jniortools.dll", "jniortools.dll");
    }

    public static void main(String[] args) {
        SpringApplication.run(OrtoolsApplication.class, args);
    }
}
