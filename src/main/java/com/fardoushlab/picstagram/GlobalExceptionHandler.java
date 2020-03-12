package com.fardoushlab.picstagram;

import com.fardoushlab.picstagram.exceptions.ResourceAlreadyExistException;
import com.fardoushlab.picstagram.exceptions.ResourceNotFoundException;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@Configuration
@ControllerAdvice
public class GlobalExceptionHandler  {


    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public String handleResourceNotFound(HttpServletRequest request, Exception e, Model model){

        model.addAttribute("errMsg",e.getMessage());
        e.printStackTrace();

        return "error";
    }

    @ResponseStatus(code = HttpStatus.CONFLICT)
    @ExceptionHandler(ResourceAlreadyExistException.class)
    public String handleResourceAlreadyExists(HttpServletRequest request, Exception e, Model model){

        model.addAttribute("errMsg",e.getMessage());
        e.printStackTrace();
        return "error";

    }

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public String handleInternalServerError(HttpServletRequest req, Exception e, Model model){

        model.addAttribute("errMsg",e.getMessage());
        e.printStackTrace();
        return "error";
    }
}






















