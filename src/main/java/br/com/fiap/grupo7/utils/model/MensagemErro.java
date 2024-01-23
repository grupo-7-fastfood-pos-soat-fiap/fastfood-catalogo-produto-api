package br.com.fiap.grupo7.utils.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class MensagemErro {

    private String mensagem;

    private String codigo;
}
