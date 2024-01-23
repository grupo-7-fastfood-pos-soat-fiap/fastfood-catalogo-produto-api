package br.com.fiap.grupo7.adapters.input.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlteraPrecoRequest extends CriaProdutoRequest {

    private Double preco;

}

