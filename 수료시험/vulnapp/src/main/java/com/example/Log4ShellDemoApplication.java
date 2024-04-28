package com.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Log4ShellDemoApplication {

    private static final Logger logger = LogManager.getLogger(Log4ShellDemoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(Log4ShellDemoApplication.class, args);
    }

    @GetMapping("/log")
    public String log(@RequestParam String message) {
        logger.info(message);
        return "Logged: " + message;
    }

    // Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
    // then press Enter. You can now see whitespace characters in your code.
    public static class Main {
        public static void main(String[] args) {
            // Press Opt+Enter with your caret at the highlighted text to see how
            // IntelliJ IDEA suggests fixing it.
            System.out.printf("Hello and welcome!");

            // Press Ctrl+R or click the green arrow button in the gutter to run the code.
            for (int i = 1; i <= 5; i++) {

                // Press Ctrl+D to start debugging your code. We have set one breakpoint
                // for you, but you can always add more by pressing Cmd+F8.
                System.out.println("i = " + i);
            }
        }
    }
}
