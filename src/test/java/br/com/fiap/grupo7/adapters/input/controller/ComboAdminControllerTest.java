package br.com.fiap.grupo7.adapters.input.controller;

import br.com.fiap.grupo7.adapters.input.model.*;
import br.com.fiap.grupo7.domains.Combo;
import br.com.fiap.grupo7.domains.Promocao;
import br.com.fiap.grupo7.domains.services.ComboAdminService;
import br.com.fiap.grupo7.domains.services.ComboClienteService;
import br.com.fiap.grupo7.utils.PedidoHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ComboAdminControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ComboAdminService comboAdminService;

    @Mock
    private ComboClienteService comboClienteService;

    AutoCloseable mock;

    @BeforeEach
    void setup() {
        mock = MockitoAnnotations.openMocks(this);
        ComboAdminController comboAdminController = new ComboAdminController(comboAdminService, comboClienteService);
        mockMvc = MockMvcBuilders.standaloneSetup(comboAdminController)
                .build();
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    @Test
    void deveCriarProduto() throws Exception {
        CriaComboRequest criaComboRequest = new CriaComboRequest();
        criaComboRequest.setProdutos(new ArrayList<>());
        criaComboRequest.setNome("Super combo 2.0");
        criaComboRequest.setPreco(12.66);

        when(comboAdminService.criarCombo(any())).thenReturn(any());

        mockMvc.perform(post("/admin/combos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(PedidoHelper.asJsonString(criaComboRequest)))
                .andExpect(status().isCreated());
        verify(comboAdminService, times(1)).criarCombo(any());
    }

    @Test
    void deveAlterarCombo() throws Exception {
        AlteraComboRequest alteraComboRequest = new AlteraComboRequest();
        alteraComboRequest.setNome("Sandúiche");

        Combo combo = new Combo();
        combo.setId(UUID.fromString("259bdc02-1ab5-11ee-be56-0242ac120002"));

        when(comboAdminService.obterCombo(anyString())).thenReturn(combo);
        when(comboAdminService.alterarCombo(anyString(), any(AlteraComboRequest.class))).thenReturn(combo);

        mockMvc.perform(put("/admin/combos/{id}", "126")
                .contentType(MediaType.APPLICATION_JSON)
                .content(PedidoHelper.asJsonString(alteraComboRequest)))
                .andExpect(status().isOk());

        verify(comboAdminService, times(1)).alterarCombo(anyString(), any());
    }

    @Test
    void deveAlterarPrecoDoCombo() throws Exception {
        AlteraPrecoRequest alteraPrecoRequest = new AlteraPrecoRequest();
        alteraPrecoRequest.setPreco(26.57);

        when(comboAdminService.alterarPrecoDoCombo(anyString(), anyDouble())).thenReturn(true);

        mockMvc.perform(put("/admin/combos/{id}/preco", "126")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(PedidoHelper.asJsonString(alteraPrecoRequest)))
                .andExpect(status().isNoContent());

        verify(comboAdminService, times(1)).alterarPrecoDoCombo(anyString(), anyDouble());
    }

    @Test
    void deveCadastrarPromocaoAoCombo() throws Exception {
        PromocaoRequest promocaoRequest = new PromocaoRequest();
        promocaoRequest.setAtiva(true);
        promocaoRequest.setPreco(26.57);

        when(comboAdminService.cadastrarPromocaoAoCombo(anyString(), any(PromocaoRequest.class))).thenReturn(true);

        mockMvc.perform(put("/admin/combos/{id}/cadastra-promocao", "126")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(PedidoHelper.asJsonString(promocaoRequest)))
                .andExpect(status().isNoContent());

        verify(comboAdminService, times(1)).cadastrarPromocaoAoCombo(anyString(), any());
    }

    @Test
    void deveRemoverPromocaoDoCombo() throws Exception {
        Combo combo = new Combo();
        combo.setId(UUID.fromString("259bdc02-1ab5-11ee-be56-0242ac120002"));

        combo.setPromocao(Promocao.builder().ativa(true).preco(12.0).nome("").build());

        when(comboAdminService.obterCombo(anyString())).thenReturn(combo);
        when(comboAdminService.removerPromocaoDoCombo(anyString())).thenReturn(true);

        mockMvc.perform(put("/admin/combos/{id}/remove-promocao", "126"))
                .andExpect(status().isNoContent());

        verify(comboAdminService, times(1)).removerPromocaoDoCombo(anyString());
    }

    @Test
    void excluirProduto() throws Exception {
        when(comboAdminService.excluirCombo(anyString())).thenReturn(true);

        mockMvc.perform(delete("/admin/combos/{id}", "126"))
                .andExpect(status().isNoContent());

        verify(comboAdminService, times(1)).excluirCombo(anyString());
    }

}
