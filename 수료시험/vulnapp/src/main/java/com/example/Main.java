package com.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        if (args.length > 0) {
            logger.info("Received: {}", args[0]);
        } else {
            logger.info("Hello, world!");
        }
    }
}
