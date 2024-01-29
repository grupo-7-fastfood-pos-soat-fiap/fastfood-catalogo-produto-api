package br.com.fiap.grupo7.adapters.input.controller;

import br.com.fiap.grupo7.domains.Produto;
import br.com.fiap.grupo7.domains.services.ProdutoClienteService;
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

public class ProdutoClienteControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProdutoClienteService produtoClienteService;

    @Mock
    private ProdutoClienteController produtoClienteController;

    AutoCloseable mock;

    @BeforeEach
    void setup() {
        mock = MockitoAnnotations.openMocks(this);
        ProdutoClienteController produtoClienteController = new ProdutoClienteController(produtoClienteService);
        mockMvc = MockMvcBuilders.standaloneSetup(produtoClienteController)
                .build();
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    @Test
    void deveObterProduto() throws Exception {
        Produto produto = new Produto();

        when(produtoClienteService.obterProduto(anyString())).thenReturn(produto);

        mockMvc.perform(get("/produtos/{id}", "126")).andExpect(status().isOk());
        verify(produtoClienteService, times(1)).obterProduto(anyString());
    }

    @Test
    void deveObterProdutoPorNome() throws Exception {
        Produto produto = new Produto();

        when(produtoClienteService.obterProdutoPorNome(anyString())).thenReturn(produto);

        mockMvc.perform(get("/produtos/nome?nome=Sanduíche")).andExpect(status().isOk());
        verify(produtoClienteService, times(1)).obterProdutoPorNome(anyString());
    }

    @Test
    void deveListarProdutos() throws Exception {
        when(produtoClienteService.listarProdutos()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/produtos")).andExpect(status().isOk());
        verify(produtoClienteService, times(1)).listarProdutos();
    }

    @Test
    void deveListarProdutosPorCategoria() throws Exception {
        when(produtoClienteService.listarProdutosPorCategoria(any())).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/produtos/categoria?categoria=Bebida")).andExpect(status().isOk());
        verify(produtoClienteService, times(1)).listarProdutosPorCategoria(any());
    }

    @Test
    void deveListarProdutosComPromocaoAtiva() throws Exception {
        when(produtoClienteService.listarProdutosComPromocaoAtiva()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/produtos/promocao-ativa")).andExpect(status().isOk());
        verify(produtoClienteService, times(1)).listarProdutosComPromocaoAtiva();
    }

}
