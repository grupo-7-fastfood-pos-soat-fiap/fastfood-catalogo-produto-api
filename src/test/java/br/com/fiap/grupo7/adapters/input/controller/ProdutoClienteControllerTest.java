//package br.com.fiap.grupo7.adapters.input.controller;
//
//import br.com.fiap.grupo7.domains.services.ProdutoAdminService;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.fail;
//
//public class PedidoControllerTest {
//
//    private MockMvc mockMvc;
//
//    @Mock
//    private ProdutoAdminService produtoAdminService;
//
//    AutoCloseable mock;
//
//    private final UUID ID_CLIENTE = UUID.fromString("0727595e-e75a-4ad9-ae62-2fe81d4c2193");
//    private final UUID ID_PEDIDO = UUID.fromString("914b7e70-3e0c-4bd7-89c3-595b0db89205");
//
//    @BeforeEach
//    void setup() {
//        mock = MockitoAnnotations.openMocks(this);
//        ProdutoAdminController pedidoController = new ProdutoAdminController(produtoAdminService);
//        mockMvc = MockMvcBuilders.standaloneSetup(pedidoController)
//                .build();
//    }
//
//    @AfterEach
//    void tearDown() throws Exception {
//        mock.close();
//    }
//
//    //@Test
//    //void deveCriarPedido() throws Exception {
//    //    Pedido pedido = criarPerdido(new Cliente());
//    //    when(produtoAdminService.criarPedido(any(Pedido.class))).thenReturn(pedido);
////
//     //   mockMvc.perform(post("/pedidos").content(PedidoHelper.asJsonString(pedido))).andExpect(status().isCreated());
//     //   verify(produtoAdminService, times(1)).criarPedido(any(Pedido.class));
//    //}
//
//    @Test
//    void deveBuscarTodosPedidos() {
//        fail("deveBuscarTodosPedidos");
//    }
//
//    @Test
//    void deveEditarPedido() {
//        fail("deveEditarPedido");
//    }
//
//    @Test
//    void deveBuscarPedidoPorIdentificador() {
//        fail("deveBuscarPedidoPorIdentificador");
//    }
//
//    @Test
//    void deveBuscarTodosPedidosDaUltimaHora() {
//        fail("deveBuscarTodosPedidosDaUltimaHora");
//    }
//
//    @Test
//    void deveBuscarTodosPedidosPedidosDoDia() {
//        fail("deveBuscarTodosPedidosPedidosDoDia");
//    }
//
//    @Test
//    void deveBuscarTodosPedidosDaUltimaHoraPorStatusDeAndamento() {
//        fail("deveBuscarTodosPedidosDaUltimaHoraPorStatusDeAndamento");
//    }
//
//    @Test
//    void deveBuscarHistoricoDePedidosDoClienteSelecionado() {
//        fail("deveBuscarHistoricoDePedidosDoClienteSelecionado");
//    }
//
//    @Test
//    void deveBuscarPedidoDoClientePorStatusDeAndamento() {
//        fail("deveBuscarPedidoDoClientePorStatusDeAndamento");
//    }
//
//}
