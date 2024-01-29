package br.com.fiap.grupo7.ports.input;

import br.com.fiap.grupo7.adapters.input.model.CategoriaRequest;
import br.com.fiap.grupo7.domains.Categoria;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoriaAdminInputInterface {

    ResponseEntity<?> criarCategoria(CategoriaRequest categoriaRequest) throws Exception;
    ResponseEntity<List<Categoria>> listarCategorias();

    ResponseEntity<Boolean> excluirCategoria(String id);

}
