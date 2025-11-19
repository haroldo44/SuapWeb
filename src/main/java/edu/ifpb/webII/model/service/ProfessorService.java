package edu.ifpb.webII.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.ifpb.webII.model.Curso;
import edu.ifpb.webII.model.Professor;
import edu.ifpb.webII.repository.ProfessorRepository;


@Service
public class ProfessorService {
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	@Transactional(readOnly = true)
	public List<Professor> listarProfessores(){
		return professorRepository.findAll();
	}
	
	@Transactional(readOnly = false)
	public Professor cadastrarProfessor(Professor professor) {
		return professorRepository.save(professor);
	}
	
	@Transactional(readOnly = false)
	public Professor atualizarProfessor(Professor professor) {
		return professorRepository.save(professor);
	}
	
	@Transactional(readOnly = false)
	public String deletarProfessor(Professor professor) {
		Long matricula = professor.getMatricula();
		professorRepository.deleteById(matricula);
		return "Professor de matricula " + matricula + " deletado com sucesso";
	}

	@Transactional(readOnly = true)
	public Professor listarProfessor(Long matricula) {
		Professor professor = (Professor) professorRepository.findById(matricula).orElse(null); 
		return professor;
	}
	
	@Transactional(readOnly = false)
	public String deletarProfID(Long matricula) {
		professorRepository.deleteById(matricula);
		return "Professor de matricula " + matricula + " deletado com sucesso";		
	}

}
