package br.com.fiap.grupo7.adapters.input.controller;

import br.com.fiap.grupo7.domains.Combo;
import br.com.fiap.grupo7.domains.services.ComboClienteService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ComboClienteControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ComboClienteService comboClienteService;

    AutoCloseable mock;

    @BeforeEach
    void setup() {
        mock = MockitoAnnotations.openMocks(this);
        ComboClienteController comboClienteController = new ComboClienteController(comboClienteService);
        mockMvc = MockMvcBuilders.standaloneSetup(comboClienteController)
                .build();
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    @Test
    void deveObterCombo() throws Exception {
        Combo combo = new Combo();

        when(comboClienteService.obterCombo(anyString())).thenReturn(combo);

        mockMvc.perform(get("/combos/{id}", "126")).andExpect(status().isOk());
        verify(comboClienteService, times(1)).obterCombo(anyString());
    }

    @Test
    void deveObterComboPorNome() throws Exception {
        Combo combo = new Combo();

        when(comboClienteService.obterComboPorNome(anyString())).thenReturn(combo);

        mockMvc.perform(get("/combos/nome?nome=Supercombo")).andExpect(status().isOk());
        verify(comboClienteService, times(1)).obterComboPorNome(anyString());
    }

    @Test
    void deveListarCombos() throws Exception {
        when(comboClienteService.listarCombos()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/combos")).andExpect(status().isOk());
        verify(comboClienteService, times(1)).listarCombos();
    }

    @Test
    void deveListarProdutosComPromocaoAtiva() throws Exception {
        when(comboClienteService.listarCombosComPromocaoAtiva()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/combos/promocao-ativa")).andExpect(status().isOk());
        verify(comboClienteService, times(1)).listarCombosComPromocaoAtiva();
    }

}
