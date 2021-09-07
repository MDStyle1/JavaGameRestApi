package com.mds.gameserver;

import com.mds.gameserver.chat.ChatListenUser;
import com.mds.gameserver.windows.Window;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

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
    @Bean
    public List<ChatListenUser> listenUserList(){
        return new ArrayList<ChatListenUser>();
    }
    @Bean
    public List<String> listMessage() { return new ArrayList<String>();}
//    @Bean
//    public ChatListenService chatListenService(){
//        return new ChatListenService();
//    }
    @Bean
    public Window window(){
        return new Window();
    }
}
