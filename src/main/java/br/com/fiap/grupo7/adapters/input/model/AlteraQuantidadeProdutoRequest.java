package br.com.fiap.grupo7.adapters.input.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlteraQuantidadeProdutoRequest extends CriaProdutoRequest {

    private Integer quantidade;

}

