package br.com.fiap.grupo7.ports.input;

import br.com.fiap.grupo7.adapters.input.model.*;
import org.springframework.http.ResponseEntity;

public interface ComboAdminInputInterface extends ComboClienteInputInterface{
    ResponseEntity<?> criarCombo(CriaComboRequest produto) throws Exception;

    ResponseEntity<?> alterarCombo(String id, AlteraComboRequest produto) throws Exception;

    ResponseEntity<Boolean> alterarPrecoDoCombo(String id, AlteraPrecoRequest quantidade) throws Exception;

    ResponseEntity<?> cadastrarPromocaoAoCombo(String id, PromocaoRequest promocao) throws Exception;

    ResponseEntity<Boolean> removerPromocaoDoCombo(String id) throws Exception;

    ResponseEntity<Boolean> excluirCombo(String id);
}
