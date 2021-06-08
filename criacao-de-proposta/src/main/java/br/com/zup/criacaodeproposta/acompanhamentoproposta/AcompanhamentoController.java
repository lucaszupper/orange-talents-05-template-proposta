package br.com.zup.criacaodeproposta.acompanhamentoproposta;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.criacaodeproposta.novaproposta.Proposta;
import br.com.zup.criacaodeproposta.novaproposta.PropostaRepository;

@RestController
@RequestMapping("/api/proposta")
public class AcompanhamentoController {

    private PropostaRepository repository;

    @Timed(value = "detalhe.proposta.request", histogram = false,  extraTags = {"emissora", "Mastercard", "banco", "itau"})
    @GetMapping("/{idProposta}")
    public ResponseEntity<?> consulta(@PathVariable(name = "idProposta") Long idProposta){

        System.out.println("chamou o metodo");
        Optional<Proposta> optional = repository.findById(idProposta);
        if(optional.isPresent()){
            
            return ResponseEntity.ok().body(new FormDetalheProposta(optional.get()));
        }

        return ResponseEntity.notFound().build();

        
    }


    public AcompanhamentoController(PropostaRepository repository){
        this.repository = repository;
    }

}
