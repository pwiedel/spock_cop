package com.pmw.rps.config;

import com.pmw.rps.RockPaperScissors;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Paul on 10/11/2015.
 */
@Configuration
public class RPSConfig {
    @Bean(name="rockPaperScissors")
    public RockPaperScissors rockPaperScissors() {
        return new RockPaperScissors();
    }
}
