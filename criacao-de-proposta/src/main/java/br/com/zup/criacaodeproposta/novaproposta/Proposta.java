package br.com.zup.criacaodeproposta.novaproposta;

import java.math.BigDecimal;

import javax.persistence.Column;
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
    @Column(unique = true, nullable = false)
    private @NotBlank @NotNull String documento;
    @Column(nullable = false)
    private @NotNull @NotBlank @Email String email;
    @Column(nullable = false)
    private @NotNull @NotBlank String nome;
    @Column(nullable = false)
    private @NotBlank @NotNull String endereco;
    @Column(nullable = false)
    private @NotNull @Min(0) BigDecimal salario;

    public Proposta(@NotBlank @NotNull String documento, @NotNull @NotBlank @Email String email,
            @NotNull @NotBlank String nome, @NotBlank @NotNull String endereco, @NotNull @Min(0) BigDecimal salario) {
                this.documento = limpaString(documento);
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

    public static String limpaString(String string){
        return string.replace(".", "").replace("-", "").replace("/", "").trim();
    }
}
