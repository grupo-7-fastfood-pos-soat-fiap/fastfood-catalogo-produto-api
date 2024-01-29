package br.com.fiap.grupo7.adapters.input.controller;

import br.com.fiap.grupo7.adapters.input.model.*;
import br.com.fiap.grupo7.domains.Categoria;
import br.com.fiap.grupo7.domains.services.CategoriaService;
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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CategoriaControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CategoriaService categoriaService;

    @Mock
    private CategoriaController categoriaController;

    AutoCloseable mock;

    @BeforeEach
    void setup() {
        mock = MockitoAnnotations.openMocks(this);
        CategoriaController categoriaController = new CategoriaController(categoriaService);
        mockMvc = MockMvcBuilders.standaloneSetup(categoriaController)
                .build();
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    @Test
    void deveCriarCategoria() throws Exception {
        var categoriaRequest = CategoriaRequest.builder().nome("Bebidas").build();

        when(categoriaService.criarCategoria(any())).thenReturn(new Categoria());

        mockMvc.perform(post("/admin/produtos/categorias").contentType(MediaType.APPLICATION_JSON)
                .content(PedidoHelper.asJsonString(categoriaRequest)))
                .andExpect(status().isCreated());
        verify(categoriaService, times(1)).criarCategoria(any());
    }

    @Test
    void deveListarCategorias() throws Exception {
        when(categoriaService.listarCategorias()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/admin/produtos/categorias").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(categoriaService, times(1)).listarCategorias();
    }


    @Test
    void deveExcluirCategoria() throws Exception {
        when(categoriaService.excluirCategoria(anyString())).thenReturn(true);

        mockMvc.perform(delete("/admin/produtos/categorias/{id}", "126"))
                .andExpect(status().isNoContent());

        verify(categoriaService, times(1)).excluirCategoria(anyString());
    }

}
