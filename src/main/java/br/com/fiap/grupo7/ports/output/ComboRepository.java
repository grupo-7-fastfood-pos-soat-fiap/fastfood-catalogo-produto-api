package br.com.fiap.grupo7.ports.output;

import br.com.fiap.grupo7.domains.Combo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ComboRepository extends JpaRepository<Combo, UUID> {

    Optional<Combo> findByNome(String nome);

    List<Combo> findAllByPromocaoAtiva(Boolean ativa);

}
