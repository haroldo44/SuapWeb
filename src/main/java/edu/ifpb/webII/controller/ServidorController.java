package edu.ifpb.webII.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.ifpb.webII.model.Servidor;
import edu.ifpb.webII.model.service.ServidorService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/servidores")
public class ServidorController {
	
	@Autowired
	private ServidorService servidorService;
	
	@GetMapping("/cadastrar")
	public String cadastrar(Servidor servidor) {
		return "/servidores/cadastro";
	}
	
	@PostMapping("/salvar")
	public String salvar(@Valid Servidor servidor, BindingResult result, RedirectAttributes attr) {
		if(result.hasErrors()) {
			return "servidores/cadastro";
		}
		servidorService.cadastrarServidor(servidor);
		attr.addFlashAttribute("sucesso", "Servidor salvo com sucesso!");
		return "redirect:/servidores/cadastrar";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
	    List<Servidor> servidores = servidorService.listarServidor();
	    model.addAttribute("servidores", servidores);
	    return "servidores/lista";
	}
	
	@GetMapping("/editar/{codigo}")
	public String preEditar(@PathVariable("codigo") Long codigo, ModelMap model) {
		Servidor servidor = servidorService.listarServidor(codigo);
		
		if (servidor == null) {
			model.addAttribute("erro", "Servidor não encontrado!");
			return listar(model);
		}

		model.addAttribute("servidor", servidor);
		return "/servidores/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(@Valid Servidor servidor, BindingResult result, RedirectAttributes attr) {
		if(result.hasErrors()) {
			return "servidores/cadastro";
		}

		servidorService.atualizarServidor(servidor);
		attr.addFlashAttribute("sucesso", "Servidor editado com sucesso!");
		return "redirect:/servidores/cadastrar";
	}

	@GetMapping("/excluir/{codigo}")
	public String excluir(@PathVariable("codigo") Long codigo, ModelMap model) {
		servidorService.deletarServidorporID(codigo);
		model.addAttribute("sucesso","Servidor excluído com sucesso!");
		return listar(model);
	}
}
