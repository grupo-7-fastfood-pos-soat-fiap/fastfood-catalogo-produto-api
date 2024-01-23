package br.com.fiap.grupo7.ports.input;

import br.com.fiap.grupo7.adapters.input.model.*;
import br.com.fiap.grupo7.domains.Promocao;
import org.springframework.http.ResponseEntity;

public interface ProdutoAdminInputInterface extends ProdutoClienteInputInterface {
    ResponseEntity<?> criarProduto(CriaProdutoRequest produto) throws Exception;

    ResponseEntity<?> alterarProduto(String id, AlteraProdutoRequest produto) throws Exception;

    ResponseEntity<Boolean> alterarQuantidadeDisponivelEstoque(String id, AlteraQuantidadeProdutoRequest quantidade) throws Exception;

    ResponseEntity<Boolean> alterarPrecoDoProduto(String id, AlteraPrecoRequest quantidade) throws Exception;

    ResponseEntity<?> cadastrarPromocaoAoProduto(String id, PromocaoRequest promocao) throws Exception;

    ResponseEntity<Boolean> removerPromocaoDoProduto(String id) throws Exception;

    ResponseEntity<Boolean> excluirProduto(String id);
}
