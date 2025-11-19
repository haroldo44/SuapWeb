package edu.ifpb.webII.model;

import java.util.Objects;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="SERVIDOR")
public class Servidor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="servidor_seq")
    @SequenceGenerator(name="servidor_seq", sequenceName="SERVIDOR_SEQ", allocationSize=1)
    @Column(name = "CODIGO")
    private Long codigo;

    @NotBlank(message = "Informe o nome do servidor")
    @Size(min = 4, max = 50, message="O nome deve ter entre {min} e {max} caracteres")
    @Pattern(regexp = "^[A-Za-zÀ-ÿ\\s]+$", message = "O nome deve conter apenas letras")
    @Column(name = "NOME", nullable = false, length = 50)
    private String nome;
    
    @NotBlank(message = "Informe a área do servidor")
    @Size(min = 4, max = 50, message="A área deve ter entre {min} e {max} caracteres")
    @Pattern(regexp = "^[A-Za-zÀ-ÿ\\s]+$", message = "A área deve conter apenas letras")
    @Column(name = "AREA", nullable = false, length = 50)
    private String area;

    public Long getCodigo() { return codigo; }
    public void setCodigo(Long codigo) { this.codigo = codigo; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getArea() { return area; }
    public void setArea(String area) { this.area = area; }

    @Override
    public int hashCode() { return Objects.hash(codigo); }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Servidor other = (Servidor) obj;
        return Objects.equals(codigo, other.codigo);
    }
}
