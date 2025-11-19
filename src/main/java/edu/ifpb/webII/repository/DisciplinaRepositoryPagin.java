package edu.ifpb.webII.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import edu.ifpb.webII.model.Disciplina;

public interface DisciplinaRepositoryPagin extends PagingAndSortingRepository<Disciplina,Long> {

	Page<Disciplina> findAll(Pageable pageable);

}
