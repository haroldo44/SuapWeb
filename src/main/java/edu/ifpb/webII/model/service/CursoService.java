package edu.ifpb.webII.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.ifpb.webII.model.Curso;
import edu.ifpb.webII.repository.CursoRepository;


@Service
public class CursoService {
	
	@Autowired
	private CursoRepository cursorepository;
	
	@Transactional(readOnly = true)
	public List<Curso> listarCursos(){
		return cursorepository.findAll();
	}
	
	@Transactional(readOnly = false)
	public Curso cadastrarCurso(Curso curso) {
		return cursorepository.save(curso);
	}
	
	@Transactional(readOnly = false)
	public Curso atualizarCurso(Curso curso) {
		return cursorepository.save(curso);
	}

	
	@Transactional(readOnly = true)
	public Curso listarCurso(Long codigo) {
		Curso curso = (Curso) cursorepository.findById(codigo).orElse(null); 
		return curso;
	}
	
	@Transactional(readOnly = false)
	public String deletarCurso(Curso curso) {
		Long codigo = curso.getCodigo();
		cursorepository.deleteById(codigo);
		return "Curso de codigo " + codigo + " deletado com sucesso";
	}
	
	@Transactional(readOnly = false)
	public String deletarCursoporID(Long codigo) {
		cursorepository.deleteById(codigo);
		return "Curso de codigo " + codigo + " deletado com sucesso";
	}

}
