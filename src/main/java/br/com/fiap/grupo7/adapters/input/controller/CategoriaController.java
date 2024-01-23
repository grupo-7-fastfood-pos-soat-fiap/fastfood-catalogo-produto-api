package br.com.fiap.grupo7.adapters.input.controller;

import br.com.fiap.grupo7.adapters.input.model.CategoriaRequest;
import br.com.fiap.grupo7.domains.Categoria;
import br.com.fiap.grupo7.domains.services.CategoriaService;
import br.com.fiap.grupo7.utils.PedidoHelper;
import br.com.fiap.grupo7.utils.model.MensagemErro;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("admin/produtos/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @PostMapping()
    public ResponseEntity<?> criarCategoria(@RequestBody CategoriaRequest request) throws Exception {
        try {

            if (request.getNome() == null) {
                return new ResponseEntity<>(PedidoHelper.asJsonString(new MensagemErro("A categoria precisa de um nome", "")), HttpStatus.BAD_REQUEST);
            }

            var categoria = Categoria.builder()
                    .nome(request.getNome())
                    .build();

            return new ResponseEntity<>(categoriaService.criarCategoria(categoria), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(PedidoHelper.asJsonString(new MensagemErro(e.getMessage(), "")), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping()
    public ResponseEntity<List<Categoria>> listarCategorias() {
        return new ResponseEntity<>(this.categoriaService.listarCategorias(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> excluirCategoria(@PathVariable String id) {
        return new ResponseEntity<>(this.categoriaService.excluirCategoria(id), HttpStatus.NO_CONTENT);
    }

}
