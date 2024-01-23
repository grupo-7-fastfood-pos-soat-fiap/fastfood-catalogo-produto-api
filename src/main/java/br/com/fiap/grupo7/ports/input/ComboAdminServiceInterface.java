package br.com.fiap.grupo7.ports.input;

import br.com.fiap.grupo7.adapters.input.model.AlteraComboRequest;
import br.com.fiap.grupo7.adapters.input.model.PromocaoRequest;
import br.com.fiap.grupo7.domains.Combo;

public interface ComboAdminServiceInterface extends ComboClienteServiceInterface {

    Combo criarCombo(Combo combo) throws Exception;

    Combo alterarCombo(String id, AlteraComboRequest combo)  throws Exception;

    Boolean alterarPrecoDoCombo(String id, Double preco) throws Exception;

    Boolean cadastrarPromocaoAoCombo(String id, PromocaoRequest request) throws Exception;

    Boolean removerPromocaoDoCombo(String id) throws Exception;

    Boolean excluirCombo(String id);

}
