
package com.ai.gemini_chat;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;

@RestController
public class CustomErrorController implements ErrorController {
    
    @RequestMapping("/error")
    public ResponseEntity<String> handleError() {
        return ResponseEntity.ok("API is running. Please use /api/qna/ask endpoint for queries.");
    }
}
