package br.com.fiap.grupo7.ports.input;

import br.com.fiap.grupo7.domains.Combo;
import br.com.fiap.grupo7.domains.Produto;

import java.util.List;

public interface ComboClienteServiceInterface {

    List<Combo> listarCombos();

    List<Combo> listarCombosComPromocaoAtiva();

    Combo obterCombo(String id);

    Combo obterComboPorNome(String nome);
}
