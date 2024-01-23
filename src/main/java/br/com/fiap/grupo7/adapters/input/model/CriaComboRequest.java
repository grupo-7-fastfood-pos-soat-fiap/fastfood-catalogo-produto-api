package br.com.fiap.grupo7.adapters.input.model;

import br.com.fiap.grupo7.domains.Imagem;
import br.com.fiap.grupo7.domains.Produto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class CriaComboRequest {

    private String nome;

    private String descricao;

    private Double preco;

    private List<Produto> produtos;

    private List<Imagem> imagens;

}

