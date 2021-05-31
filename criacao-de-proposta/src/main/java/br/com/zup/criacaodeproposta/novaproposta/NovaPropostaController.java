package br.com.zup.criacaodeproposta.novaproposta;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/proposta")
public class NovaPropostaController {
    
    private PropostaRepository propostaRepository;

    public NovaPropostaController(PropostaRepository propostaRepository){
        this.propostaRepository = propostaRepository;
    }

    @PostMapping
    public ResponseEntity<PropostaDto> cria(@RequestBody @Valid PropostaDto request, UriComponentsBuilder builder){
        
        Proposta proposta = propostaRepository.save(request.toModel());

        URI uri = builder.path("/api/proposta/{id}").buildAndExpand(proposta.getId()).toUri();
        return ResponseEntity.created(uri).body(new PropostaDto(proposta));
    }
}
