package edu.ifpb.webII.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.ifpb.webII.model.Aluno;
import edu.ifpb.webII.model.Curso;
import edu.ifpb.webII.model.service.AlunoService;
import edu.ifpb.webII.model.service.CursoService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private CursoService cursoService;

    @GetMapping("/cadastrar")
    public String cadastrar(Aluno aluno) {
        return "/alunos/cadastro";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Aluno aluno, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "alunos/cadastro";
        }
        alunoService.cadastraAluno(aluno);
        attr.addFlashAttribute("sucesso", "Aluno salvo com sucesso!");
        return "redirect:/alunos/cadastrar";
    }

    @GetMapping("/listar")
    public String listar(ModelMap model) {
        List<Aluno> alunos = alunoService.listarAlunos();
        model.addAttribute("alunos", alunos);
        return "/alunos/lista";
    }

    @GetMapping("/editar/{matricula}")
    public String preEditar(@PathVariable("matricula") Long matricula, ModelMap model) {
        Aluno aluno = alunoService.listarAluno(matricula);
        model.addAttribute("aluno", aluno);
        return "/alunos/cadastro";
    }

    @PostMapping("/editar")
    public String editar(@Valid Aluno aluno, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "alunos/cadastro";
        }
        alunoService.atualizarAluno(aluno);
        attr.addFlashAttribute("sucesso", "Aluno editado com sucesso!");
        return "redirect:/alunos/cadastrar";
    }

    @GetMapping("excluir/{matricula}")
    public String excluir(@PathVariable("matricula") Long matricula, ModelMap model) {
        alunoService.deletarAlunoporID(matricula);
        model.addAttribute("sucesso","Aluno excluído com sucesso!");
        return listar(model);
    }

    @ModelAttribute("cursos")
    public List<Curso> getCursos(){
        return cursoService.listarCursos();
    }

    @GetMapping("buscar/nome")
    public String getAlunoPorNome(@RequestParam("nome") String nome, ModelMap model) {
        model.addAttribute("alunos", alunoService.listarAlunosNome(nome));
        return "/alunos/lista";
    }

    @GetMapping("buscar/curso")
    public String getAlunoPorNome(@RequestParam("codigo") Long codigo, ModelMap model) {
        model.addAttribute("alunos", alunoService.listarAlunosCodigo(cursoService.listarCurso(codigo)));
        return "/alunos/lista";
    }
}
