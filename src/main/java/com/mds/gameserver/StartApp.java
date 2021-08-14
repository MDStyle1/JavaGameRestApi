package com.mds.gameserver;

import com.mds.gameserver.windows.Window;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.ColorModel;

@SpringBootApplication
public class StartApp {
    public static void main(String[] args) {
//        SpringApplication.run(StartApp.class,args);
        SpringApplicationBuilder builder = new SpringApplicationBuilder(StartApp.class);
        builder.headless(false);
        ConfigurableApplicationContext context = builder.run(args);
    }
    @PostConstruct
    public void post(){
    }
//    @Bean
//    public Window window(){
//        return new Window();
//    }
}
