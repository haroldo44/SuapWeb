package edu.ifpb.webII.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import edu.ifpb.webII.model.AcessoNegadoException;

@ControllerAdvice
public class ExceptionController {

    private static final Logger log = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler(AcessoNegadoException.class)
    public ModelAndView acessoNegadoException(AcessoNegadoException ex) {
        ModelAndView model = new ModelAndView("error");
        model.addObject("status", 403);
        model.addObject("error", "Operação não pode ser realizada.");
        model.addObject("message", ex.getMessage());
        return model;
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ModelAndView dataIntegrityViolationException(DataIntegrityViolationException ex) {
        log.error("Violacão na integracão dos dados.", ex.getMessage(), ex);
        ModelAndView model = new ModelAndView("error");
        model.addObject("status", 500);
        model.addObject("error", "Operação não pode ser realizada.");
        model.addObject("message", "Violacão na integracão dos dados.");
        return model;
    }
}