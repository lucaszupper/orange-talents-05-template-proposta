package br.com.zup.criacaodeproposta.consultadadosfinanceiro;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(url = "http://localhost:9999", name = "consultaRestricao")
public interface ConsultaFinanceira {

    @RequestMapping(value="/api/solicitacao", method=RequestMethod.POST, consumes = "application/json")
    FormConsultaRestricao consultaRestricao(FormConsultaRestricao request);
    
}
