package com.pmw.rps;

import com.pmw.rps.config.RPSConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Paul on 10/11/2015.
 */
@SpringBootApplication
public class RockPapersScissorsApplication {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(RPSConfig.class);
    }
}
