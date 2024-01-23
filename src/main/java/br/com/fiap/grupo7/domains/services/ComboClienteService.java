package br.com.fiap.grupo7.domains.services;

import br.com.fiap.grupo7.domains.Combo;
import br.com.fiap.grupo7.ports.input.ComboClienteServiceInterface;
import br.com.fiap.grupo7.ports.output.ComboRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ComboClienteService implements ComboClienteServiceInterface {

    private ComboRepository comboRepository;

    public ComboClienteService(ComboRepository combooRepository) {
        this.comboRepository = combooRepository;
    }

    public List<Combo> listarCombos() {

        return this.comboRepository.findAll();
    }

    public List<Combo> listarCombosComPromocaoAtiva() {

        return this.comboRepository.findAllByPromocaoAtiva(true);
    }

    public Combo obterCombo(String id) {
        var combo = this.comboRepository.findById(UUID.fromString(id));

        if (combo.isPresent()) {
            return combo.get();
        } else {
            return null;
        }
    }

    public Combo obterComboPorNome(String nome) {
        var combo = this.comboRepository.findByNome(nome);

        if (combo.isPresent()) {
            return combo.get();
        } else {
            return null;
        }
    }

}
