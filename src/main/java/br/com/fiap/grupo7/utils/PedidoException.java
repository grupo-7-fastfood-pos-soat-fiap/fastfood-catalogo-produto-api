package br.com.fiap.grupo7.utils;

import br.com.fiap.grupo7.utils.model.MensagemErro;

public class PedidoException extends Exception {

    public PedidoException() {
        new MensagemErro("teste", "baba");
    }
}
