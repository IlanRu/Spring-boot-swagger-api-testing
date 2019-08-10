package com.sabugo.spring.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(ItemRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Item(1,"Ilan", 10, 1234)));
            log.info("Preloading " + repository.save(new Item(2,"Rubens", 15, 4321)));
        };
    }
}