package br.com.fiap.grupo7.domains.services;

import br.com.fiap.grupo7.domains.Produto;
import br.com.fiap.grupo7.ports.input.ProdutoClienteServiceInterface;
import br.com.fiap.grupo7.ports.output.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProdutoClienteService implements ProdutoClienteServiceInterface {

    private ProdutoRepository combooRepository;

    public ProdutoClienteService(ProdutoRepository combooRepository) {
        this.combooRepository = combooRepository;
    }

    public List<Produto> listarProdutos() {

        return this.combooRepository.findAll();
    }

    public List<Produto> listarProdutosPorCategoria(String id) {
        return this.combooRepository.findAllByCategoriaId(UUID.fromString(id));
    }

    public List<Produto> listarProdutosComPromocaoAtiva() {

        return this.combooRepository.findAllByPromocaoAtiva(true);
    }

    public Produto obterProduto(String id) {
        var produto = this.combooRepository.findById(UUID.fromString(id));

        if (produto.isPresent()) {
            return produto.get();
        } else {
            return null;
        }
    }

    public Produto obterProdutoPorNome(String nome) {
        var produto = this.combooRepository.findByNome(nome);

        if (produto.isPresent()) {
            return produto.get();
        } else {
            return null;
        }
    }

}
