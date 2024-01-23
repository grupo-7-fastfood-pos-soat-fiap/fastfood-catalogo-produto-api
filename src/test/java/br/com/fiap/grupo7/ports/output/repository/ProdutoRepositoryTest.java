package br.com.fiap.grupo7.ports.output.repository;

import br.com.fiap.grupo7.ports.output.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@AutoConfigureTestDatabase
@Transactional
//@RunWith(SpringRunner.class)
@SpringBootTest()
public class ProdutoRepositoryTest {


    @Autowired
    @Mock
    private ProdutoRepository produtoRepository;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void deveCriarTabelaDePedidos() {
        long totalTabelasCriada = produtoRepository.count();
        assertThat(totalTabelasCriada).isNotNegative();
    }

//    @Test
//    void deveCriarPedido() {
//        //var pedidoRequisicao = this.criarPerdido(new Cliente());
//
//        when(produtoRepository.save(any(Produto.class))).thenReturn(pedidoRequisicao);
//
//        var pedidoResponse = produtoRepository.save(pedidoRequisicao);
//
//        verify(produtoRepository, times(1)).save(any());
//        assertThat(pedidoRequisicao).isNotNull().isEqualTo(pedidoResponse);
//        assertThat(pedidoRequisicao.getId()).isEqualTo(pedidoResponse.getId());
//    }

//    @Test
//    void deveBuscarTodosProdutos() {
//        var pedidoRequisicao1 = this.criarPerdido(new Cliente());
//
//        var pedidoRequisicao2 = this.criarPerdido(new Cliente());
//        pedidoRequisicao2.setId(UUID.fromString("0727595e-e75a-4ad9-ae62-2fe81d4c2193"));
//
//        var pedidoRequisicao3 = this.criarPerdido(new Cliente());
//        pedidoRequisicao3.setId(UUID.fromString("914b7e70-3e0c-4bd7-89c3-595b0db89205"));
//
//        List<Produto> pedidos = new ArrayList<>();
//        pedidos.add((pedidoRequisicao1));
//        pedidos.add((pedidoRequisicao2));
//        pedidos.add((pedidoRequisicao3));
//
//        when(produtoRepository.findAll()).thenReturn(pedidos);
//
//        produtoRepository.findAll();
//
//        verify(produtoRepository, times(1)).findAll();
//        assertThat(produtoRepository.findAll()).isNotNull().isEqualTo(pedidos);
//    }

//    @Test
//    void deveBuscarPedidoPorId() {
//        var pedido1 = this.criarPerdido(new Cliente());
//
//        var pedido2 = this.criarPerdido(new Cliente());
//        pedido2.setId(UUID.fromString("0727595e-e75a-4ad9-ae62-2fe81d4c2193"));
//
//        var pedido3 = this.criarPerdido(new Cliente());
//        pedido3.setId(UUID.fromString("914b7e70-3e0c-4bd7-89c3-595b0db89205"));
//
//        List<Pedido> pedidos = new ArrayList<>();
//        pedidos.add((pedido1));
//        pedidos.add((pedido2));
//        pedidos.add((pedido3));
//
//        when(produtoRepository.findById(UUID.fromString("0727595e-e75a-4ad9-ae62-2fe81d4c2193"))).thenReturn(Optional.of(pedido2));
//
//        Optional<Pedido> pedidoResponse = produtoRepository.findById(UUID.fromString("0727595e-e75a-4ad9-ae62-2fe81d4c2193"));
//
//        verify(produtoRepository, times(1)).findById(any());
//        pedidoResponse.ifPresent(pedido ->
//                assertThat(pedido).isNotNull().isEqualTo(Optional.of(pedido2).get())
//        );
//    }

    @Test
    void deveAlterarProduto() {
        fail("deve alterar produto");
    }

    @Test
    void deveObterProdutoPorNome() {
        fail("deve buscar produto por nome");
    }

    @Test
    void deveListarProdutosPorCategoria() {
        fail("deve listar produtos pela categoria");
    }

    @Test
    void deveListarProdutosComPromocaoAtiva() {
        fail("deve listar produtos com promoção ativa");
    }

//    @Test
//    void deveExcluirProduto() {
//        var pedido1 = this.criarPerdido(new Cliente());
//
//        var pedido2 = this.criarPerdido(new Cliente());
//        pedido2.setId(UUID.fromString("0727595e-e75a-4ad9-ae62-2fe81d4c2193"));
//
//        var pedido3 = this.criarPerdido(new Cliente());
//        pedido3.setId(UUID.fromString("914b7e70-3e0c-4bd7-89c3-595b0db89205"));
//
//        List<Pedido> pedidos = new ArrayList<>();
//        pedidos.add((pedido1));
//        pedidos.add((pedido2));
//        pedidos.add((pedido3));
//
//        when(produtoRepository.findAll()).thenReturn(pedidos);
//        doNothing().when(produtoRepository).deleteById(pedido2.getId());
//
//        produtoRepository.deleteById(pedido2.getId());
//
//        verify(produtoRepository, times(1)).deleteById(any());
//    }

}
