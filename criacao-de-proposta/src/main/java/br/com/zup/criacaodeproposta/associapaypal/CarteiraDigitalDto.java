package br.com.zup.criacaodeproposta.associapaypal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CarteiraDigitalDto {
    @NotNull @NotBlank @Email
    private String email;
    private TipoCarteira carteira;

    public CarteiraDigitalDto(String email, TipoCarteira carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public TipoCarteira getCarteira() {
        return carteira;
    }

    public CarteiraDigitalDto(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public CarteiraDigitalDto() {
    }
}
