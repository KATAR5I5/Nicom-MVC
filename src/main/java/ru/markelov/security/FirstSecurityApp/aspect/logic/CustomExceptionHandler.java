package ru.markelov.security.FirstSecurityApp.aspect.logic;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CustomExceptionHandler implements HandlerExceptionResolver {

//    @ExceptionHandler(RuntimeException.class)
//    public String exeptionsAll(){
//        return "exceptionPage";
//    }

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        return new ModelAndView("exceptionPage");
    }
}
