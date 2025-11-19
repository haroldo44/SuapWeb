package edu.ifpb.webII.model;

import java.util.Objects;
import jakarta.persistence.*;

@Entity
@Table(name = "Matricula")
public class Matricula {

    @EmbeddedId
    private MatriculaID id;

    @ManyToOne
    @MapsId("matricula_aluno")
    @JoinColumn(name = "matricula_aluno")
    private Aluno aluno;

    @ManyToOne
    @MapsId("cod_disciplina")
    @JoinColumn(name = "cod_disciplina")
    private Disciplina disciplina;

    public Matricula() {}
    public Matricula(MatriculaID id, Aluno aluno, Disciplina disciplina) {
        this.id = id;
        this.aluno = aluno;
        this.disciplina = disciplina;
    }

    public MatriculaID getId() { return id; }
    public void setId(MatriculaID id) { this.id = id; }

    public Aluno getAluno() { return aluno; }
    public void setAluno(Aluno aluno) { this.aluno = aluno; }

    public Disciplina getDisciplina() { return disciplina; }
    public void setDisciplina(Disciplina disciplina) { this.disciplina = disciplina; }

    public String getPeriodo() { return id.getPeriodo_matricula(); }

    @Override
    public int hashCode() { return Objects.hash(id); }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Matricula)) return false;
        Matricula other = (Matricula) obj;
        return Objects.equals(id, other.id);
    }
}
