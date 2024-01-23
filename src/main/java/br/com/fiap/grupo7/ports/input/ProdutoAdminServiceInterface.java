package br.com.fiap.grupo7.ports.input;

import br.com.fiap.grupo7.adapters.input.model.AlteraProdutoRequest;
import br.com.fiap.grupo7.adapters.input.model.PromocaoRequest;
import br.com.fiap.grupo7.domains.Produto;

public interface ProdutoAdminServiceInterface extends ProdutoClienteServiceInterface {

    Produto criarProduto(Produto produto) throws Exception;

    Produto alterarProduto(String id, AlteraProdutoRequest produto)  throws Exception;

    Boolean alterarQuantidadeDisponivelEstoque(String id, Integer quantidade) throws Exception;

    Boolean alterarPrecoDoProduto(String id, Double preco) throws Exception;

    Boolean cadastrarPromocaoAoProduto(String id, PromocaoRequest request) throws Exception;

    Boolean removerPromocaoDoProduto(String id) throws Exception;

    Boolean excluirProduto(String id);

}
