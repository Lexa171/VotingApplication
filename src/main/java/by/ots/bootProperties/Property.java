package by.ots.bootProperties;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableConfigurationProperties
public class Property {

    @Bean
    @ConfigurationProperties
    public BootConfig bootConfig() {
        return new BootConfig();
    }

    public static class BootConfig {

        private Map<String, Object> voting = new HashMap<>();

        public Map<String, Object> getVoting() {
            return this.voting;
        }

        public void setVoting(Map<String, Object> voting) {
            this.voting = voting;
        }
    }
}
