package edu.ifpb.webII.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.ifpb.webII.model.Disciplina;
import edu.ifpb.webII.repository.DisciplinaRepository;
import edu.ifpb.webII.repository.DisciplinaRepositoryPagin;
import edu.ifpb.webII.util.PaginacaoUtil;

@Service
public class DisciplinaService {
		
	@Autowired
	private DisciplinaRepository disciplinaRepository;
	
	@Autowired
	private DisciplinaRepositoryPagin disciplinaRepositotyPagin;
	
	
	@Transactional(readOnly = true)
	public List<Disciplina> listarDisciplinas(){
		return disciplinaRepository.findAll();
	}
	
	@Transactional(readOnly = false)
	public Disciplina cadastrarDisciplina(Disciplina disciplina) {
		return disciplinaRepository.save(disciplina);
	}
	
	@Transactional(readOnly = false)
	public Disciplina atualizarDisciplina(Disciplina disciplina) {
		return disciplinaRepository.save(disciplina);
	}
	
	@Transactional(readOnly = false)
	public String deletarDisciplina(Disciplina disciplina) {
		Long codigo = disciplina.getCodigo();
		disciplinaRepository.deleteById(codigo);
		return "Disciplina de codigo " + codigo + " deletado com sucesso";
	}

	@Transactional(readOnly = true)
	public Disciplina listarDisciplina(Long codigo) {
		Disciplina disciplina = (Disciplina) disciplinaRepository.findById(codigo).orElse(null); 
		return disciplina;
	}
	
	@Transactional(readOnly = false)
	public String deletarDisciplinaID(Long codigo) {
		disciplinaRepository.deleteById(codigo);
		return "Disciplina de codigo " + codigo + " deletado com sucesso";		
	}

	public PaginacaoUtil<Disciplina> buscarPorPagina(int paginaAtual) {
		
		int tamanho = 5;
		Pageable pagina = PageRequest.of(paginaAtual-1, tamanho);
		List<Disciplina> disciplinas =  disciplinaRepositotyPagin.findAll(pagina).toList();
		long totalRegistros = disciplinaRepository.count();
		long totalDePaginas = (totalRegistros + (tamanho - 1)) / tamanho;
		return new PaginacaoUtil<>(tamanho, paginaAtual, totalDePaginas,  disciplinas);
	}
	

}
