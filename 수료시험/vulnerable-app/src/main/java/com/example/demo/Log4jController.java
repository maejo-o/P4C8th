package com.example.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Log4jController {

    private static final Logger logger = LogManager.getLogger(Log4jController.class);

    // GET 요청 처리 메서드
    @GetMapping("/log")
    @ResponseBody
    public String logInputGet(@RequestParam String input) { // 메서드 이름 변경
        logger.info("User input (GET): {}", input); // 로그 메시지 수정
        return "Logged: " + input;
    }

    // POST 요청 처리 메서드
    @PostMapping("/log")
    @ResponseBody // `@ResponseBody` 추가
    public String logInputPost(@RequestParam(name = "logInput", required = true) String logInput) { // 메서드 이름 변경
        logger.info("User input (POST): {}", logInput); // 로그 메시지 수정
        return "Logged: " + logInput; // 반환 타입 및 메시지 수정
    }
    public void executeLogging(){
        System.out.println("Logging executed");
    }

}
