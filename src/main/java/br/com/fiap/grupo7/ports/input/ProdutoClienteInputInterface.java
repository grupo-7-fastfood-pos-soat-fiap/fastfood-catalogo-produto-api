package br.com.fiap.grupo7.ports.input;

import br.com.fiap.grupo7.domains.Produto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProdutoClienteInputInterface {
    ResponseEntity<List<Produto>> listarProdutos();

    ResponseEntity<List<Produto>> listarProdutosPorCategoria(String id);

    ResponseEntity<List<Produto>> listarProdutosComPromocaoAtiva();

    ResponseEntity<?> obterProduto(String id);

    ResponseEntity<?> obterProdutoPorNome(String nome);

}
