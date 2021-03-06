package com.ws.kislev;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class WebApplication {
  public static void main(String[] args) {
    SpringApplication.run(WebApplication.class,args);
  }

  @Bean
  public CommandLineRunner init(){
    return new CommandLineRunner() {
      @Override
      public void run(String... args) throws Exception {
        System.out.print("项目启动成功");
      }
    };
  }
}
