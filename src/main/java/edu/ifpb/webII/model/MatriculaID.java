package edu.ifpb.webII.model;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Embeddable
public class MatriculaID implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Column(name = "matricula_aluno")
    private Long matricula_aluno;

    @NotNull
    @Column(name = "cod_disciplina")
    private Long cod_disciplina;

    @NotNull
    @Pattern(regexp = "^\\d{4}\\.[1-2]$")
    @Column(name = "periodo_matricula")
    private String periodo_matricula;

    public MatriculaID() {}
    public MatriculaID(Long matricula_aluno, Long cod_disciplina, String periodo_matricula) {
        this.matricula_aluno = matricula_aluno;
        this.cod_disciplina = cod_disciplina;
        this.periodo_matricula = periodo_matricula;
    }

    public Long getMatricula_aluno() { return matricula_aluno; }
    public void setMatricula_aluno(Long matricula_aluno) { this.matricula_aluno = matricula_aluno; }

    public Long getCod_disciplina() { return cod_disciplina; }
    public void setCod_disciplina(Long cod_disciplina) { this.cod_disciplina = cod_disciplina; }

    public String getPeriodo_matricula() { return periodo_matricula; }
    public void setPeriodo_matricula(String periodo_matricula) { this.periodo_matricula = periodo_matricula; }

    @Override
    public int hashCode() { return Objects.hash(cod_disciplina, matricula_aluno, periodo_matricula); }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof MatriculaID)) return false;
        MatriculaID other = (MatriculaID) obj;
        return Objects.equals(cod_disciplina, other.cod_disciplina) &&
               Objects.equals(matricula_aluno, other.matricula_aluno) &&
               Objects.equals(periodo_matricula, other.periodo_matricula);
    }
}
