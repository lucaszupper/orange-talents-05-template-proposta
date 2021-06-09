package br.com.zup.criacaodeproposta.associapaypal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarteiraDigitalRepository extends JpaRepository<CarteiraDigital,Long> {

    Optional<CarteiraDigital> findByCartaoIdAndTipoCarteira(String idCartao, TipoCarteira tipoCarteira);
}
