package br.com.fiap.grupo7.ports.output;

import br.com.fiap.grupo7.domains.Combo;
import br.com.fiap.grupo7.domains.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, UUID> {

    Optional<Produto> findByNome(String nome);

    List<Produto> findAllByCategoriaId(UUID id);

    List<Produto> findAllByPromocaoAtiva(Boolean ativa);
}
