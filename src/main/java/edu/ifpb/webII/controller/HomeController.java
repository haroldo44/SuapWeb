package edu.ifpb.webII.controller;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    // Página inicial (após login)
    @GetMapping("/home")
    public String home() {
        return "home"; // precisa existir home.html
    }

    // Página de login
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/login-error")
    public String loginError(ModelAndView model, HttpServletRequest req, HttpSession session) {
        
        String lastException = (String) session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION");

        if (lastException != null && lastException.contains(SessionAuthenticationException.class.getName())) {
            model.addObject("alerta", "alerta");
            model.addObject("erro", "erro");
            model.addObject("titulo", "Acesso recusado!");
            model.addObject("texto", "Você já está logado em outro dispositivo.");
            model.addObject("subtexto", "Faça o logout e espere sua sessão expirar.");
            
        } else if (lastException != null && lastException.contains(BadCredentialsException.class.getName())) {
            model.addObject("alerta", "erro");
            model.addObject("erro", "erro");
            model.addObject("titulo", "Credenciais Inválidas!");
            model.addObject("texto", "Login ou senha incorretos, tente novamente.");
            model.addObject("subtexto", "Acesso permitido apenas para cadastros já ativados.");
            
        } else {
            model.addObject("alerta", "erro");
            model.addObject("erro", "erro");
            model.addObject("titulo", "Falha de Login");
            model.addObject("texto", "Ocorreu um erro desconhecido durante o login.");
            model.addObject("subtexto", "Verifique suas credenciais e tente novamente.");
        }
        
        session.removeAttribute("SPRING_SECURITY_LAST_EXCEPTION");
        
        return "login";
    }

    @GetMapping("/expired")
    public String sessaoExpirada(ModelAndView model) {
        model.addObject("alerta", "alerta");
        model.addObject("erro", "erro");
        model.addObject("titulo", "Acesso recusado!");
        model.addObject("texto", "Sua sessão expirou.");
        model.addObject("subtexto", "Você logou em outro dispositivo");
        
        return "login";
    }
}
