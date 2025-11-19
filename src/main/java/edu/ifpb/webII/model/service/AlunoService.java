package edu.ifpb.webII.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.ifpb.webII.model.Aluno;
import edu.ifpb.webII.model.AlunoDTO;
import edu.ifpb.webII.model.Curso;
import edu.ifpb.webII.model.Professor;
import edu.ifpb.webII.repository.AlunoRepository;


@Service
public class AlunoService {
	
	@Autowired
	private AlunoRepository alunorepository;
	
	@Transactional(readOnly = true)
	public List<Aluno> listarAlunos(){
		return alunorepository.findAll();
	}
	
	@Transactional(readOnly = false)
	public Aluno cadastraAluno(Aluno aluno) {
		return alunorepository.save(aluno);
	}
	
	@Transactional(readOnly = false)
	public Aluno atualizarAluno(Aluno aluno) {
		return alunorepository.save(aluno);
	}
	
	@Transactional(readOnly = false)
	public String deletarAluno(Aluno aluno) {
		Long matricula = aluno.getMatricula();
		alunorepository.deleteById(aluno.getMatricula());
		return "Aluno de matricula " + matricula + " deletado com sucesso";
	}
	
	@Transactional(readOnly = true)
	public List<AlunoDTO> listarAlunosDTOProfessor(Professor professor){
		Long cod_professor = professor.getMatricula();
		return alunorepository.findAlunoDTOByProfessor(cod_professor);
	}
	
	@Transactional(readOnly = true)
	public List<Aluno> listarAlunosProfessor(Professor professor){
		Long cod_professor = professor.getMatricula();
		return alunorepository.findAlunoByProfessor(cod_professor);
	}
	
	@Transactional(readOnly = true)
	public Aluno listarAluno(Long matricula) {
		Aluno aluno = alunorepository.findById(matricula).orElse(null);
		return aluno;
	}
	
	@Transactional(readOnly = false)
	public String deletarAlunoporID(Long matricula) {
		alunorepository.deleteById(matricula);
		return "Aluno de matricula " + matricula + " deletado com sucesso";
		
	}
	@Transactional(readOnly = true)
	public List<Aluno> listarAlunosNome(String nome) {
		
		return alunorepository.findByNomeContainingIgnoreCase(nome);
	}
	
	@Transactional(readOnly = false)
	public List<Aluno> listarAlunosCodigo(Curso curso) {
		return alunorepository.findByCurso(curso);
	}

}
