package br.com.fiap.grupo7.adapters.input.model;

import br.com.fiap.grupo7.domains.Categoria;
import br.com.fiap.grupo7.domains.Imagem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class AlteraProdutoRequest {

    private String nome;

    private String descricao;

    private Categoria categoria;

    private List<Imagem> imagens;
}

