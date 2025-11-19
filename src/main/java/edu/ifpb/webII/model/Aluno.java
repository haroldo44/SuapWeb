package edu.ifpb.webII.model;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="Aluno")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="aluno_seq")
    @SequenceGenerator(name="aluno_seq", sequenceName="aluno_seq", allocationSize=1)
    private Long matricula;

    @NotBlank(message = "O nome do aluno é obrigatório")
    private String nome;

    @Valid
    @Embedded
    private Endereco endereco;

    @NotNull(message = "Você deve selecionar um curso")
    @ManyToOne
    @JoinColumn(name="cod_curso")
    private Curso curso;

    @OneToMany(mappedBy = "aluno")
    private List<Matricula> disciplinas;

    // Getters e Setters

    public Long getMatricula() {
		return matricula;
	}

	public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public List<Matricula> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Matricula> disciplinas) {
		this.disciplinas = disciplinas;
	}

    @Override
    public int hashCode() {
        return Objects.hash(matricula);
    }

	@Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Aluno other = (Aluno) obj;
        return Objects.equals(matricula, other.matricula);
    }

    @Override
    public String toString() {
        return "Aluno [matricula=" + matricula + ", nome=" + nome + "]";
    }
}
