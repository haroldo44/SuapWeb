package edu.ifpb.webII.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import edu.ifpb.webII.model.*;
import edu.ifpb.webII.model.service.*;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/matriculas")
public class MatriculaController {

    @Autowired
    private MatriculaService matriculaService;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private DisciplinaService disciplinaService;

    @GetMapping("/cadastrar")
    public String cadastrar(ModelMap model) {
        model.addAttribute("alunoMatricula", new AlunoMatriculaDTO());
        model.addAttribute("alunos", alunoService.listarAlunos());
        model.addAttribute("listaDisciplinas", disciplinaService.listarDisciplinas());
        return "/matriculas/cadastro";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid AlunoMatriculaDTO alunoMatricula, BindingResult result, ModelMap model, RedirectAttributes attr) {
        if (result.hasErrors()) {
            model.addAttribute("alunos", alunoService.listarAlunos());
            model.addAttribute("listaDisciplinas", disciplinaService.listarDisciplinas());
            return "/matriculas/cadastro";
        }

        Aluno aluno = alunoService.listarAluno(alunoMatricula.getMatricula());
        if (aluno == null) {
            result.rejectValue("matricula", "error.matricula", "Aluno não encontrado");
            model.addAttribute("alunos", alunoService.listarAlunos());
            model.addAttribute("listaDisciplinas", disciplinaService.listarDisciplinas());
            return "/matriculas/cadastro";
        }

        List<Matricula> matriculas = new ArrayList<>();
        for (Long codDisc : alunoMatricula.getDisciplinas()) {
            Disciplina disciplina = disciplinaService.listarDisciplina(codDisc);
            if (disciplina == null) continue;
            MatriculaID id = new MatriculaID(alunoMatricula.getMatricula(), codDisc, alunoMatricula.getPeriodo());
            Matricula m = new Matricula(id, aluno, disciplina);
            matriculas.add(m);
        }

        if (matriculas.isEmpty()) {
            result.rejectValue("disciplinas", "error.disciplinas", "Selecione pelo menos uma disciplina válida");
            model.addAttribute("alunos", alunoService.listarAlunos());
            model.addAttribute("listaDisciplinas", disciplinaService.listarDisciplinas());
            return "/matriculas/cadastro";
        }

        matriculaService.cadastrarMatricula(matriculas);
        attr.addFlashAttribute("sucesso", "Matrícula salva com sucesso!");
        return "redirect:/matriculas/cadastrar";
    }

    @GetMapping("/listar")
    public String listar(ModelMap model) {
        model.addAttribute("disciplinasPorPeriodo", matriculaService.buscarDisciplinasporPeriodo(alunoService.listarAluno(Long.valueOf(2))));
        return "/matriculas/lista";
    }

    @GetMapping("/editar/{matricula}")
    public String preEditar(@PathVariable("matricula") MatriculaID matriculaID, ModelMap model) {
        Matricula matricula = matriculaService.listarMatricula(matriculaID);
        model.addAttribute("matricula", matricula);
        model.addAttribute("alunos", alunoService.listarAlunos());
        model.addAttribute("listaDisciplinas", disciplinaService.listarDisciplinas());
        return "/matriculas/cadastro";
    }

    @PostMapping("/editar")
    public String editar(@Valid Matricula matricula, BindingResult result, RedirectAttributes attr, ModelMap model) {
        if (result.hasErrors()) {
            model.addAttribute("alunos", alunoService.listarAlunos());
            model.addAttribute("listaDisciplinas", disciplinaService.listarDisciplinas());
            return "/matriculas/cadastro";
        }
        matriculaService.atualizarMatricula(matricula);
        attr.addFlashAttribute("sucesso", "Matricula editada com sucesso!");
        return "redirect:/matriculas/cadastrar";
    }

    @GetMapping("excluir/{matricula}")
    public String excluir(@PathVariable("matricula") MatriculaID matriculaID, ModelMap model) {
        matriculaService.deletarMatriculaID(matriculaID);
        model.addAttribute("sucesso","Matricula excluída com sucesso!");
        return listar(model);
    }
}
