package edu.itstep.question.quest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandler {
    @ResponseBody
    @org.springframework.web.bind.annotation.ExceptionHandler(QuestNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
     String questNotFound(QuestNotFoundException ex) { return ex.getMessage(); }
}
