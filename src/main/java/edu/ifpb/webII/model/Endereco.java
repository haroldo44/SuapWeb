package edu.ifpb.webII.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Embeddable
public class Endereco {

    @NotBlank(message = "A rua não pode estar vazia")
    private String rua;

    @NotNull(message = "O número é obrigatório")
    @Positive(message = "O número deve ser positivo")
    private Integer numero;

    @NotBlank(message = "O bairro não pode estar vazio")
    private String bairro;

    @NotBlank(message = "A cidade não pode estar vazia")
    private String cidade;

    // Getters e Setters
    public String getRua() { return rua; }
    public void setRua(String rua) { this.rua = rua; }

    public Integer getNumero() { return numero; }
    public void setNumero(Integer numero) { this.numero = numero; }

    public String getBairro() { return bairro; }
    public void setBairro(String bairro) { this.bairro = bairro; }

    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }
}
