package br.com.fiap.grupo7.ports.input;

import br.com.fiap.grupo7.domains.Produto;

import java.util.List;

public interface ProdutoClienteServiceInterface {

    List<Produto> listarProdutos();

    List<Produto> listarProdutosPorCategoria(String id);

    List<Produto> listarProdutosComPromocaoAtiva();

    Produto obterProduto(String id);

    Produto obterProdutoPorNome(String nome);
}
