package br.com.fiap.grupo7.ports.input;

import br.com.fiap.grupo7.domains.Combo;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ComboClienteInputInterface {
    ResponseEntity<List<Combo>> listarCombos();

    ResponseEntity<List<Combo>> listarCombosComPromocaoAtiva();

    ResponseEntity<?> obterCombo(String id);

    ResponseEntity<?> obterComboPorNome(String nome);

}
