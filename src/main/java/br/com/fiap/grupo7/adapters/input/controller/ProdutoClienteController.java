package br.com.fiap.grupo7.adapters.input.controller;

import br.com.fiap.grupo7.domains.Produto;
import br.com.fiap.grupo7.domains.services.ProdutoClienteService;
import br.com.fiap.grupo7.ports.input.ProdutoClienteInputInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("produtos")
public class ProdutoClienteController implements ProdutoClienteInputInterface {

    private ProdutoClienteService produtoClienteService;

    public ProdutoClienteController(ProdutoClienteService produtoClienteService) {
        this.produtoClienteService = produtoClienteService;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Produto> obterProduto(@PathVariable String id) {
        return new ResponseEntity<>(this.produtoClienteService.obterProduto(id), HttpStatus.OK);
    }

    @GetMapping()
    @RequestMapping("/nome")
    public ResponseEntity<?> obterProdutoPorNome(String nome) {
        return new ResponseEntity<>(this.produtoClienteService.obterProdutoPorNome(nome), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Produto>> listarProdutos() {
        return new ResponseEntity<>(this.produtoClienteService.listarProdutos(), HttpStatus.OK);
    }

    @GetMapping()
    @RequestMapping("/categoria")
    public ResponseEntity<List<Produto>> listarProdutosPorCategoria(String id) {
        return new ResponseEntity<>(this.produtoClienteService.listarProdutosPorCategoria(id), HttpStatus.OK);
    }

    @RequestMapping("/promocao-ativa")
    @GetMapping()
    public ResponseEntity<List<Produto>> listarProdutosComPromocaoAtiva() {
        return new ResponseEntity<>(this.produtoClienteService.listarProdutosComPromocaoAtiva(), HttpStatus.OK);
    }
}
