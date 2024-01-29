package br.com.fiap.grupo7.adapters.input.controller;

import br.com.fiap.grupo7.adapters.input.model.*;
import br.com.fiap.grupo7.domains.Produto;
import br.com.fiap.grupo7.domains.services.CategoriaService;
import br.com.fiap.grupo7.domains.services.ProdutoAdminService;
import br.com.fiap.grupo7.domains.services.ProdutoClienteService;
import br.com.fiap.grupo7.ports.input.ProdutoAdminInputInterface;
import br.com.fiap.grupo7.utils.model.MensagemErro;
import br.com.fiap.grupo7.utils.PedidoHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("admin/produtos")
public class ProdutoAdminController extends ProdutoClienteController implements ProdutoAdminInputInterface {

    private ProdutoAdminService produtoAdminService;

    private CategoriaService categoriaService;

    private ProdutoClienteService produtoClienteService;

    public ProdutoAdminController(
            ProdutoAdminService produtoAdminService,
            ProdutoClienteService produtoClienteService,
            CategoriaService categoriaService
    ) {
        super(produtoClienteService);
        this.produtoAdminService = produtoAdminService;
        this.produtoClienteService = produtoClienteService;
        this.categoriaService = categoriaService;
    }

    @PostMapping()
    public ResponseEntity<?> criarProduto(@RequestBody CriaProdutoRequest request) throws Exception {
        try {
            var categoria = this.categoriaService.obterCategoria(request.getCategoria().getId().toString());

            if (categoria == null) {
                return new ResponseEntity<>(PedidoHelper.asJsonString(new MensagemErro("Esta categoria não existe", "")), HttpStatus.BAD_REQUEST);
            }

            Produto produto = Produto.builder()
                    .nome(request.getNome())
                    .descricao(request.getDescricao())
                    .preco(request.getPreco())
                    .categoria(request.getCategoria())
                    .quantidadeDisponivelEstoque(request.getQuantidadeDisponivelEstoque())
                    .imagens(request.getImagens())
                    .build();

            return new ResponseEntity<>(produtoAdminService.criarProduto(produto), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(PedidoHelper.asJsonString(new MensagemErro(e.getMessage(), "")), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> alterarProduto(@PathVariable String id, @RequestBody AlteraProdutoRequest request) throws Exception {
        try {
            return new ResponseEntity<>(produtoAdminService.alterarProduto(id, request), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(PedidoHelper.asJsonString(new MensagemErro(e.getMessage(), "")), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/{id}/quantidade", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> alterarQuantidadeDisponivelEstoque(@PathVariable String id, @RequestBody AlteraQuantidadeProdutoRequest request) throws Exception {
        return new ResponseEntity<>(this.produtoAdminService.alterarQuantidadeDisponivelEstoque(id, request.getQuantidade()), HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/{id}/preco", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> alterarPrecoDoProduto(@PathVariable String id, @RequestBody AlteraPrecoRequest request) throws Exception {
        return new ResponseEntity<>(this.produtoAdminService.alterarPrecoDoProduto(id, request.getPreco()), HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/{id}/cadastra-promocao", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> cadastrarPromocaoAoProduto(@PathVariable String id, @RequestBody PromocaoRequest request) throws Exception {
        return new ResponseEntity<>(this.produtoAdminService.cadastrarPromocaoAoProduto(id, request), HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/{id}/remove-promocao")
    public ResponseEntity<Boolean> removerPromocaoDoProduto(@PathVariable String id) throws Exception {
        return new ResponseEntity<>(this.produtoAdminService.removerPromocaoDoProduto(id), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> excluirProduto(@PathVariable String id) {
        return new ResponseEntity<>(this.produtoAdminService.excluirProduto(id), HttpStatus.NO_CONTENT);
    }
}
