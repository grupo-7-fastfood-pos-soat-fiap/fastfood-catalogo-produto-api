package br.com.fiap.grupo7.domains.services;

import br.com.fiap.grupo7.domains.Categoria;
import br.com.fiap.grupo7.ports.output.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public Categoria criarCategoria(Categoria categoria) {
        return this.categoriaRepository.save(categoria);
    }

    public Categoria obterCategoria(String id) {
        var categoria = this.categoriaRepository.findById(UUID.fromString(id));

        return categoria.isPresent() ? categoria.get() : null;
    }

    public List<Categoria> listarCategorias() {
        return this.categoriaRepository.findAll();
    }

    public Boolean excluirCategoria(String id) {
        this.categoriaRepository.deleteById(UUID.fromString(id));
        return true;
    }
}
