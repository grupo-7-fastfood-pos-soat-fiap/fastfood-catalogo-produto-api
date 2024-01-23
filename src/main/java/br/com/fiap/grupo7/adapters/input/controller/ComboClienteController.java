package br.com.fiap.grupo7.adapters.input.controller;

import br.com.fiap.grupo7.domains.Combo;
import br.com.fiap.grupo7.domains.services.ComboClienteService;
import br.com.fiap.grupo7.ports.input.ComboClienteInputInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("combos")
public class ComboClienteController implements ComboClienteInputInterface {

    private ComboClienteService comboClienteService;

    public ComboClienteController(ComboClienteService comboClienteService) {
        this.comboClienteService = comboClienteService;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Combo> obterCombo(@PathVariable String id) {
        return new ResponseEntity<>(this.comboClienteService.obterCombo(id), HttpStatus.OK);
    }

    @GetMapping()
    @RequestMapping("/nome")
    public ResponseEntity<?> obterComboPorNome(String nome) {
        return new ResponseEntity<>(this.comboClienteService.obterComboPorNome(nome), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Combo>> listarCombos() {
        return new ResponseEntity<>(this.comboClienteService.listarCombos(), HttpStatus.OK);
    }

    @RequestMapping("/promocao-ativa")
    @GetMapping()
    public ResponseEntity<List<Combo>> listarCombosComPromocaoAtiva() {
        return new ResponseEntity<>(this.comboClienteService.listarCombosComPromocaoAtiva(), HttpStatus.OK);
    }
}
