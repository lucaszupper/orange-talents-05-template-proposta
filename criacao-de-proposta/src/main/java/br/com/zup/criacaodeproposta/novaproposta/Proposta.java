package br.com.zup.criacaodeproposta.novaproposta;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Proposta {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private @NotBlank @NotNull String documento;
    private @NotNull @NotBlank @Email String email;
    private @NotNull @NotBlank String nome;
    private @NotBlank @NotNull String endereco;
    private @NotNull @Min(0) BigDecimal salario;

    public Proposta(@NotBlank @NotNull String documento, @NotNull @NotBlank @Email String email,
            @NotNull @NotBlank String nome, @NotBlank @NotNull String endereco, @NotNull @Min(0) BigDecimal salario) {
                this.documento = documento;
                this.email = email;
                this.nome = nome;
                this.endereco = endereco;
                this.salario = salario;
    }

    @Deprecated
    private Proposta(){}

    public Long getId() {
        return this.id;
    }


    public String getDocumento() {
        return this.documento;
    }

    public String getEmail() {
        return this.email;
    }

    public String getNome() {
        return this.nome;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public BigDecimal getSalario() {
        return this.salario;
    }

}
