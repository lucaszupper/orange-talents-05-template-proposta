package br.com.zup.criacaodeproposta.novaproposta;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.criacaodeproposta.tratamentodeerros.Erro;

@RestController
@RequestMapping("/api/proposta")
public class NovaPropostaController {
    
    private PropostaRepository propostaRepository;

    public NovaPropostaController(PropostaRepository propostaRepository){
        this.propostaRepository = propostaRepository;
    }

    @PostMapping
    public ResponseEntity<?> cria(@RequestBody @Valid PropostaDto request, UriComponentsBuilder builder){
        
        Optional<List<Proposta>> optional = propostaRepository.findByDocumento(Proposta.limpaString(request.getDocumento()) );
        if(optional.get().size() > 0){
            Erro erro = new Erro("documento", "Ja existe proposta para esse Documento");
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(erro);
        }
        
        Proposta proposta = propostaRepository.save(request.toModel());

        URI uri = builder.path("/api/proposta/{id}").buildAndExpand(proposta.getId()).toUri();
        return ResponseEntity.created(uri).body(new PropostaDto(proposta));
    }
}
