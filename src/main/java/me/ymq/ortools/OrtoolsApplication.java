package me.ymq.ortools;

import com.spring4all.swagger.EnableSwagger2Doc;
import me.ymq.ortools.util.NativeLibLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@EnableSwagger2Doc
@SpringBootApplication
public class OrtoolsApplication extends SpringBootServletInitializer {
    static {
        NativeLibLoader.load("jniortools.dll");
        NativeLibLoader.load("libortools.so");
        NativeLibLoader.load("libjniortools.so");
    }

    public static void main(String[] args) {
        SpringApplication.run(OrtoolsApplication.class, args);
    }
}
