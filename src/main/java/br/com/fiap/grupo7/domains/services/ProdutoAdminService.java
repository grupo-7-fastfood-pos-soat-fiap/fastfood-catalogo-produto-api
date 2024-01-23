package br.com.fiap.grupo7.domains.services;

import br.com.fiap.grupo7.adapters.input.model.AlteraProdutoRequest;
import br.com.fiap.grupo7.adapters.input.model.PromocaoRequest;
import br.com.fiap.grupo7.domains.Produto;
import br.com.fiap.grupo7.domains.Promocao;
import br.com.fiap.grupo7.ports.input.ProdutoAdminServiceInterface;
import br.com.fiap.grupo7.ports.output.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProdutoAdminService extends ProdutoClienteService implements ProdutoAdminServiceInterface {

    private ProdutoRepository produtoRepository;

    private final CategoriaService categoriaService;

    public ProdutoAdminService(ProdutoRepository produtoRepository, CategoriaService categoriaService) {
        super(produtoRepository);
        this.produtoRepository = produtoRepository;
        this.categoriaService = categoriaService;
    }

    public Produto criarProduto(Produto produto) throws Exception {

        if (produto.getNome() == null) {
            throw new Exception("O produto precisa ter nome");
        } else if (produto.getPreco() == null) {
            throw new Exception("O produto precisa ter preço");
        }

        var categoria = this.categoriaService.obterCategoria(produto.getCategoria().getId().toString());

        if (categoria == null) {
            throw new Exception("O produto precisa estar vinculado a uma categoria válida");
        }

        return this.produtoRepository.save(produto);
    }

    public Produto alterarProduto(String id, AlteraProdutoRequest request) throws Exception {

        var produto = this.obterProduto(id);

        if (produto != null) {
            Produto produtoAlterado = Produto.builder()
                    .id(UUID.fromString(id))
                    .nome(request.getNome())
                    .descricao(request.getDescricao())
                    .preco(produto.getPreco()) // não altera preço
                    .categoria(request.getCategoria())
                    .quantidadeDisponivelEstoque(produto.getQuantidadeDisponivelEstoque()) // não altera quantidade disponível no estoque
                    .imagens(request.getImagens())
                    .promocao(produto.getPromocao())
                    .build();

            return this.produtoRepository.save(produtoAlterado);
        } else {
            throw new Exception("Este produto não existe");
        }
    }

    public Boolean alterarQuantidadeDisponivelEstoque(String id, Integer quantidade) throws Exception {
        var produto = this.obterProduto(id);

        if (produto != null) {
            produto.setQuantidadeDisponivelEstoque(quantidade); // altera apenas quantidade disponível no estoque
            this.produtoRepository.save(produto);
            return true;
        } else {
            throw new Exception("Este produto não existe");
        }
    }

    public Boolean alterarPrecoDoProduto(String id, Double preco) throws Exception {
        var produto = this.obterProduto(id);

        if (produto != null) {
            produto.setPreco(preco); // altera apenas preco do produto
            this.produtoRepository.save(produto);
            return true;
        } else {
            throw new Exception("Este produto não existe");
        }
    }

    public Boolean cadastrarPromocaoAoProduto(String id, PromocaoRequest request) throws Exception {
        var produto = this.obterProduto(id);

        if (produto != null) {
            var promocao = Promocao.builder()
                    .produtos(null)
                    .nome(request.getNome())
                    .descricao(request.getDescricao())
                    .preco(request.getPreco())
                    .ativa(request.getAtiva())
                    .build();

            produto.setPromocao(promocao); // altera apenas apromocao
            this.produtoRepository.save(produto);
            return true;
        } else {
            throw new Exception("Este produto não existe");
        }
    }

    public Boolean removerPromocaoDoProduto(String id) throws Exception {
        var produto = this.obterProduto(id);

        if (produto != null) {
            produto.setPromocao(null); // altera apenas apromocao
            this.produtoRepository.save(produto);
            return true;
        } else {
            throw new Exception("Este produto não existe");
        }
    }

    public Boolean excluirProduto(String id) {
        this.produtoRepository.deleteById(UUID.fromString(id));
        return true;
    }
}
