package br.com.zup.criacaodeproposta.associapaypal;

import br.com.zup.criacaodeproposta.cadastracartao.Cartao;
import br.com.zup.criacaodeproposta.cadastracartao.CartaoRepository;
import br.com.zup.criacaodeproposta.tratamentodeerros.Erro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/paypal")
public class PaypalController {

    @Autowired
    private CartaoRepository cartaoRepository;
    @Autowired
    private CarteiraDigitalRepository carteiraDigitalRepository;
    @Autowired
    private CadastraCarteiraSistemaExterno cadastraCarteira;

    @PostMapping("/{idCartao}")
    public ResponseEntity<?> associa(@PathVariable(name = "idCartao") String idCartao, @RequestBody @Valid CarteiraDigitalDto carteiraDigitalDto, UriComponentsBuilder builder){
        Optional<Cartao> cartaoOptional = cartaoRepository.findById(idCartao);
        if(!cartaoOptional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        Optional<CarteiraDigital> optionalCarteiraDigital = carteiraDigitalRepository.findByCartaoIdAndTipoCarteira(idCartao,TipoCarteira.PAYPAL);
        if(optionalCarteiraDigital.isPresent()){
            Erro erro = new Erro("carteira", "Ja existe associacao desse cartao para essa carteira");
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(erro);
        }
        String idSistemaExterno = cadastraCarteira.cadastra(new CarteiraDigitalDto(carteiraDigitalDto.getEmail(), TipoCarteira.PAYPAL), idCartao);
        if(Objects.isNull(idSistemaExterno)){
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        }

        CarteiraDigital carteiraDigital = carteiraDigitalRepository.save(new CarteiraDigital(idSistemaExterno, cartaoOptional.get(), TipoCarteira.PAYPAL));
        URI uri = builder.path("/api/paypal/" + idCartao + "/{id}").buildAndExpand(carteiraDigital.getId()).toUri();

        return ResponseEntity.created(uri).body(new CarteiraDigitalRequest(carteiraDigital));
    }
}
