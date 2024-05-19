package com.example.customvalidation.controller;

import com.example.customvalidation.entity.HI1Message;
import com.example.customvalidation.exception.LevelInfoException;
import com.example.customvalidation.exception.TopLevelException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.servlet.http.HttpServletRequest;
@ControllerAdvice
public class DefaultExceptionHandler {
    private static Logger logger = LogManager.getLogger(DefaultExceptionHandler.class);

    @ExceptionHandler(value = TopLevelException.class)
    public ResponseEntity<HI1Message> topLevelExceptionHandler(HttpServletRequest req, TopLevelException e) {
        logger.error("Error code = {}, {}", e.getErrorInfo(), e.getMessage());
        HI1Message hi1Message = new HI1Message(e.getHeader(), e.getErrorInfo());
        return new ResponseEntity<>(hi1Message, HttpStatus.OK);
    }

    @ExceptionHandler(value = LevelInfoException.class)
    public ResponseEntity<HI1Message> levelInfoExceptionHandler(HttpServletRequest req, LevelInfoException e) {
        logger.error("Error code = {}, {}", e.getErrorInfo(), e.getMessage());
        // todo: check ErrorInfo and set level
        HI1Message hi1Message = new HI1Message(e.getHeader(), e.getErrorInfo());
        return new ResponseEntity<>(hi1Message, HttpStatus.OK);
    }
}
