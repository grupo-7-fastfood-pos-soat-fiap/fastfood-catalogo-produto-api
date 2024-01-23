package br.com.fiap.grupo7.adapters.input.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class PromocaoRequest {

    private String nome;

    private String descricao;

    private Double preco;

    private Boolean ativa;

}

