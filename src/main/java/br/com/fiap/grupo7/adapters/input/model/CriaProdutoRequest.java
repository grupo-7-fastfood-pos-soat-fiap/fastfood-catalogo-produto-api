package br.com.fiap.grupo7.adapters.input.model;

import br.com.fiap.grupo7.domains.Categoria;
import br.com.fiap.grupo7.domains.Imagem;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class CriaProdutoRequest {

    private String nome;

    private String descricao;

    private Double preco;

    private Categoria categoria;

    @JsonProperty("quantidade_disponivel_estoque")
    private Integer quantidadeDisponivelEstoque;

    private List<Imagem> imagens;
}

