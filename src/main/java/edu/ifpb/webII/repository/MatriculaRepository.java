package edu.ifpb.webII.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ifpb.webII.model.Aluno;
import edu.ifpb.webII.model.Matricula;
import edu.ifpb.webII.model.MatriculaID;

public interface MatriculaRepository extends JpaRepository<Matricula,MatriculaID>  {

	List<Matricula> findByAluno(Aluno aluno);
	

}
