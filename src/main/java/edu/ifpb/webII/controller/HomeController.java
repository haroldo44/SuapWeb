package edu.ifpb.webII.controller;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap; // Importe ModelMap para passar dados para a view
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.servlet.ModelAndView; // Removido
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
    public String loginError(ModelMap model, HttpServletRequest req, HttpSession session) {

        // CORRIGIDO: Recupere o objeto da exceção como Object.
        Object lastException = session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION");

        if (lastException instanceof SessionAuthenticationException) {
            
            // Tratamento para Sessão Duplicada/Expirada (Ex: Máximo de Sessões Excedido)
            model.addAttribute("alerta", "alerta");
            model.addAttribute("erro", "erro");
            model.addAttribute("titulo", "Acesso recusado!");
            model.addAttribute("texto", "Você já está logado em outro dispositivo.");
            model.addAttribute("subtexto", "Faça o logout e espere sua sessão expirar.");

        } else if (lastException instanceof BadCredentialsException) {
            
            // Tratamento para Login/Senha Inválidos
            model.addAttribute("alerta", "erro");
            model.addAttribute("erro", "erro");
            model.addAttribute("titulo", "Credenciais Inválidas!");
            model.addAttribute("texto", "Login ou senha incorretos, tente novamente.");
            model.addAttribute("subtexto", "Verifique se o seu cadastro já foi ativado.");

        } else if (lastException != null) {
             // Tratamento para outras exceções de login desconhecidas
             model.addAttribute("alerta", "erro");
             model.addAttribute("erro", "erro");
             model.addAttribute("titulo", "Falha de Login");
             model.addAttribute("texto", "Ocorreu um erro inesperado durante o login.");
             model.addAttribute("subtexto", lastException.getClass().getSimpleName() + ": " + ((Exception) lastException).getMessage());
             
        } else {
            
            // Tratamento genérico se o atributo não existir ou for null
            model.addAttribute("alerta", "erro");
            model.addAttribute("erro", "erro");
            model.addAttribute("titulo", "Falha de Login");
            model.addAttribute("texto", "Ocorreu um erro desconhecido durante o login.");
            model.addAttribute("subtexto", "Verifique suas credenciais e tente novamente.");
        }

        // Limpar o atributo de sessão para evitar que o erro reapareça em requisições futuras
        session.removeAttribute("SPRING_SECURITY_LAST_EXCEPTION");

        return "login"; // Retorna o nome da view de login
    }

    @GetMapping("/expired")
    public String sessaoExpirada(ModelMap model) { // CORRIGIDO: Usando ModelMap em vez de ModelAndView
        model.addAttribute("alerta", "alerta");
        model.addAttribute("erro", "erro");
        model.addAttribute("titulo", "Acesso recusado!");
        model.addAttribute("texto", "Sua sessão expirou.");
        model.addAttribute("subtexto", "Você logou em outro dispositivo.");

        return "login";
    }
}