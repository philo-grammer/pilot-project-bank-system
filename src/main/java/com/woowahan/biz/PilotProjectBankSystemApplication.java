package com.woowahan.biz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PilotProjectBankSystemApplication {

    private static Logger logger =
            LoggerFactory.getLogger(PilotProjectBankSystemApplication.class);

    public static void main(String[] args) {
        logger.info("=========Starting application========");
        SpringApplication.run(PilotProjectBankSystemApplication.class, args);
        logger.warn("=========Ending application=========");
    }
}
