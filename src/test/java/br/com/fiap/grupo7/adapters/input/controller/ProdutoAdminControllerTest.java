package br.com.fiap.grupo7.adapters.input.controller;

import br.com.fiap.grupo7.adapters.input.model.*;
import br.com.fiap.grupo7.domains.Categoria;
import br.com.fiap.grupo7.domains.Produto;
import br.com.fiap.grupo7.domains.Promocao;
import br.com.fiap.grupo7.domains.services.CategoriaService;
import br.com.fiap.grupo7.domains.services.ProdutoAdminService;
import br.com.fiap.grupo7.domains.services.ProdutoClienteService;
import br.com.fiap.grupo7.utils.PedidoHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProdutoAdminControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProdutoAdminService produtoAdminService;

    @Mock
    private ProdutoClienteService produtoClienteService;

    @Mock
    private CategoriaService categoriaService;

    @Mock
    private ProdutoAdminController produtoAdminController;

    AutoCloseable mock;

    @BeforeEach
    void setup() {
        mock = MockitoAnnotations.openMocks(this);
        ProdutoAdminController produtoAdminController = new ProdutoAdminController(produtoAdminService, produtoClienteService, categoriaService);
        mockMvc = MockMvcBuilders.standaloneSetup(produtoAdminController)
                .build();
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    @Test
    void deveCriarProduto() throws Exception {
        CriaProdutoRequest criaProdutoRequest = new CriaProdutoRequest();
        Categoria categoria = new Categoria();
        categoria.setId(UUID.randomUUID());

        criaProdutoRequest.setNome("Sandúiche");
        criaProdutoRequest.setPreco(10.2);
        criaProdutoRequest.setCategoria(categoria);

        when(categoriaService.obterCategoria(anyString())).thenReturn(categoria);

        when(produtoAdminService.criarProduto(any())).thenReturn(any());

        produtoAdminController.criarProduto(criaProdutoRequest);

        mockMvc.perform(post("/admin/produtos").contentType(MediaType.APPLICATION_JSON).content(PedidoHelper.asJsonString(criaProdutoRequest))).andExpect(status().isCreated());
        verify(produtoAdminService, times(1)).criarProduto(any());
    }

    @Test
    void deveAlterarProduto() throws Exception {
        AlteraProdutoRequest alteraProdutoRequest = new AlteraProdutoRequest();
        Categoria categoria = new Categoria();
        categoria.setId(UUID.fromString("259bdc02-1ab5-11ee-be56-0242ac120002"));

        alteraProdutoRequest.setNome("Sandúiche");
        alteraProdutoRequest.setCategoria(categoria);

        Produto produto = new Produto();
        produto.setId(UUID.fromString("259bdc02-1ab5-11ee-be56-0242ac120002"));

        when(produtoAdminService.obterProduto(anyString())).thenReturn(produto);
        when(produtoAdminService.alterarProduto(anyString(), any(AlteraProdutoRequest.class))).thenReturn(produto);

        mockMvc.perform(put("/admin/produtos/{id}", "126")
                .contentType(MediaType.APPLICATION_JSON)
                .content(PedidoHelper.asJsonString(alteraProdutoRequest)))
                .andExpect(status().isOk());

        verify(produtoAdminService, times(1)).alterarProduto(anyString(), any());
    }

    @Test
    void deveAlterarQuantidadeDisponivelDeProdutoNoEstoque() throws Exception {
        AlteraQuantidadeProdutoRequest alteraQuantidadeProdutoRequest = new AlteraQuantidadeProdutoRequest();
        alteraQuantidadeProdutoRequest.setQuantidade(126);

        when(produtoAdminService.alterarQuantidadeDisponivelEstoque(anyString(), anyInt())).thenReturn(true);

        mockMvc.perform(put("/admin/produtos/{id}/quantidade", "126")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(PedidoHelper.asJsonString(alteraQuantidadeProdutoRequest)))
                .andExpect(status().isNoContent());

        verify(produtoAdminService, times(1)).alterarQuantidadeDisponivelEstoque(anyString(), anyInt());
    }

    @Test
    void deveAlterarPrecoDoProduto() throws Exception {
        AlteraPrecoRequest alteraPrecoRequest = new AlteraPrecoRequest();
        alteraPrecoRequest.setPreco(26.57);

        when(produtoAdminService.alterarPrecoDoProduto(anyString(), anyDouble())).thenReturn(true);

        mockMvc.perform(put("/admin/produtos/{id}/preco", "126")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(PedidoHelper.asJsonString(alteraPrecoRequest)))
                .andExpect(status().isNoContent());

        verify(produtoAdminService, times(1)).alterarPrecoDoProduto(anyString(), anyDouble());
    }

    @Test
    void deveCadastrarPromocaoAoProduto() throws Exception {
        PromocaoRequest promocaoRequest = new PromocaoRequest();
        promocaoRequest.setAtiva(true);
        promocaoRequest.setPreco(26.57);

        when(produtoAdminService.cadastrarPromocaoAoProduto(anyString(), any(PromocaoRequest.class))).thenReturn(true);

        mockMvc.perform(put("/admin/produtos/{id}/cadastra-promocao", "126")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(PedidoHelper.asJsonString(promocaoRequest)))
                .andExpect(status().isNoContent());

        verify(produtoAdminService, times(1)).cadastrarPromocaoAoProduto(anyString(), any());
    }

    @Test
    void deveRemoverPromocaoDoProduto() throws Exception {
        Produto produto = new Produto();
        produto.setId(UUID.fromString("259bdc02-1ab5-11ee-be56-0242ac120002"));

        produto.setPromocao(Promocao.builder().ativa(true).preco(12.0).nome("").build());

        when(produtoAdminService.obterProduto(anyString())).thenReturn(produto);
        when(produtoAdminService.removerPromocaoDoProduto(anyString())).thenReturn(true);

        mockMvc.perform(put("/admin/produtos/{id}/remove-promocao", "126"))
                .andExpect(status().isNoContent());

        verify(produtoAdminService, times(1)).removerPromocaoDoProduto(anyString());
    }

    @Test
    void excluirProduto() throws Exception {
        when(produtoAdminService.excluirProduto(anyString())).thenReturn(true);

        mockMvc.perform(delete("/admin/produtos/{id}", "126"))
                .andExpect(status().isNoContent());

        verify(produtoAdminService, times(1)).excluirProduto(anyString());
    }

}
