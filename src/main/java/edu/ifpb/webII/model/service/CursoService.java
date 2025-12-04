package edu.ifpb.webII.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.ifpb.webII.model.Curso;
import edu.ifpb.webII.repository.CursoRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class CursoService {
	
	private static final Logger log = LoggerFactory.getLogger(CursoService.class);
	
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
	public String deletarCursoPorID(Long codigo) {
	    try {
	        cursorepository.deleteById(codigo);
	        log.debug("Deletando o curso de codigo " + codigo);
	        return "Curso de codigo " + codigo + " deletado com sucesso";
	    } catch (DataIntegrityViolationException e) {
	        return "Erro ao deletar o curso de codigo " + codigo +
	        ": existem registros dependentes vinculados a ele.";
	    } catch (Exception e) {
	        log.error("Erro inesperado ao deletar curso com código {}: {}", codigo, e.getMessage(), e);
	        return "Erro inesperado ao deletar o curso de código " + codigo +
	        ": " + e.getMessage();
	    }
	}

}
