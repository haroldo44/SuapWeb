package edu.ifpb.webII.model.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import edu.ifpb.webII.model.*;
import edu.ifpb.webII.repository.MatriculaRepository;

@Service
public class MatriculaService {

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Transactional(readOnly = true)
    public List<Matricula> listarMatriculas() {
        return matriculaRepository.findAll();
    }

    @Transactional(readOnly = false)
    public List<Matricula> cadastrarMatricula(List<Matricula> matriculas) {
        return matriculaRepository.saveAll(matriculas);
    }

    @Transactional(readOnly = false)
    public Matricula atualizarMatricula(Matricula matricula) {
        return matriculaRepository.save(matricula);
    }

    @Transactional(readOnly = true)
    public Matricula listarMatricula(MatriculaID matriculaID) {
        return matriculaRepository.findById(matriculaID).orElse(null);
    }

    @Transactional(readOnly = false)
    public String deletarMatricula(Matricula matricula) {
        MatriculaID matriculaID = matricula.getId();
        matriculaRepository.deleteById(matriculaID);
        return "Matricula deletada";
    }

    @Transactional(readOnly = true)
    public Map<String, List<Disciplina>> buscarDisciplinasporPeriodo(Aluno aluno) {
        List<Matricula> alunoMatricula = matriculaRepository.findByAluno(aluno);
        Map<String, List<Disciplina>> disciplinasPorPeriodo = alunoMatricula.stream()
                .collect(Collectors.groupingBy(Matricula::getPeriodo,
                        Collectors.mapping(Matricula::getDisciplina, Collectors.toList())));
        return disciplinasPorPeriodo;
    }

    @Transactional(readOnly = false)
    public String deletarMatriculaID(MatriculaID matriculaID) {
        matriculaRepository.deleteById(matriculaID);
        return "Matricula deletada";
    }
}
