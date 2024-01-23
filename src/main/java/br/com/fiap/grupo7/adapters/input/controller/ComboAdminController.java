package br.com.fiap.grupo7.adapters.input.controller;

import br.com.fiap.grupo7.adapters.input.model.*;
import br.com.fiap.grupo7.domains.Combo;
import br.com.fiap.grupo7.domains.services.*;
import br.com.fiap.grupo7.ports.input.ComboAdminInputInterface;
import br.com.fiap.grupo7.utils.PedidoHelper;
import br.com.fiap.grupo7.utils.model.MensagemErro;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/combos")
public class ComboAdminController extends ComboClienteController implements ComboAdminInputInterface {

    private ComboAdminService comboAdminService;

    private ComboClienteService comboClienteService;

    public ComboAdminController(
            ComboAdminService comboAdminService,
            ComboClienteService comboClienteService
    ) {
        super(comboClienteService);
        this.comboAdminService = comboAdminService;
        this.comboClienteService = comboClienteService;
    }

    @PostMapping()
    public ResponseEntity<?> criarCombo(@RequestBody CriaComboRequest request) throws Exception {
        try {
            // TODO verficar se os produtos existem

            // var categoria = this.categoriaService.obterCategoria(request.getCategoria().getId().toString());

            // if (categoria == null) {
            //    return new ResponseEntity<>(PedidoHelper.asJsonString(new MensagemErro("Esta categoria não existe", "")), HttpStatus.BAD_REQUEST);
            //}

            Combo combo = Combo.builder()
                    .nome(request.getNome())
                    .descricao(request.getDescricao())
                    .preco(request.getPreco())
                    .produtos(request.getProdutos())
                    .imagens(request.getImagens())
                    .build();

            return new ResponseEntity<>(comboAdminService.criarCombo(combo), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(PedidoHelper.asJsonString(new MensagemErro(e.getMessage(), "")), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> alterarCombo(@PathVariable String id, @RequestBody AlteraComboRequest request) throws Exception {
        try {
            return new ResponseEntity<>(comboAdminService.alterarCombo(id, request), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(PedidoHelper.asJsonString(new MensagemErro(e.getMessage(), "")), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/{id}/preco", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> alterarPrecoDoCombo(@PathVariable String id, @RequestBody AlteraPrecoRequest request) throws Exception {
        return new ResponseEntity<>(this.comboAdminService.alterarPrecoDoCombo(id, request.getPreco()), HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/{id}/cadastra-promocao", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> cadastrarPromocaoAoCombo(@PathVariable String id, @RequestBody PromocaoRequest request) throws Exception {
        return new ResponseEntity<>(this.comboAdminService.cadastrarPromocaoAoCombo(id, request), HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/{id}/remove-promocao", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> removerPromocaoDoCombo(@PathVariable String id) throws Exception {
        return new ResponseEntity<>(this.comboAdminService.removerPromocaoDoCombo(id), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> excluirCombo(@PathVariable String id) {
        return new ResponseEntity<>(this.comboAdminService.excluirCombo(id), HttpStatus.NO_CONTENT);
    }
}
