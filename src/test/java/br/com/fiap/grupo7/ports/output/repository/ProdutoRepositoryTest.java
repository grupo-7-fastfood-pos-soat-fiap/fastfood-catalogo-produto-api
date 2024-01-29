package br.com.fiap.grupo7.ports.output.repository;

import br.com.fiap.grupo7.domains.Produto;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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

    @Test
    void deveCriarProduto() {
        var produtoReq = new Produto();

        when(produtoRepository.save(any(Produto.class))).thenReturn(produtoReq);

        var pedidoResponse = produtoRepository.save(produtoReq);

        verify(produtoRepository, times(1)).save(any());
        assertThat(produtoReq).isNotNull().isEqualTo(pedidoResponse);
        assertThat(produtoReq.getId()).isEqualTo(pedidoResponse.getId());
    }

    @Test
    void deveListarTodosProdutos() {
        var produtoReq1 = new Produto();
        var produtoReq2 = new Produto();
        var produtoReq3 = new Produto();

        List<Produto> produtos = new ArrayList<>();
        produtos.add((produtoReq1));
        produtos.add((produtoReq2));
        produtos.add((produtoReq3));

        when(produtoRepository.findAll()).thenReturn(produtos);

        produtoRepository.findAll();

        verify(produtoRepository, times(1)).findAll();
        assertThat(produtoRepository.findAll()).isNotNull().isEqualTo(produtos);
    }

    @Test
    void deveBuscarPedidoPorId() {
        var produtoReq1 = new Produto();
        produtoReq1.setId(UUID.fromString("0727595e-e75a-4ad9-ae62-2fe81d4c2193"));
        var produtoReq2 = new Produto();
        produtoReq2.setId(UUID.fromString("0727595e-e75a-4ad9-ae62-2fe81d4c2193"));
        var produtoReq3 = new Produto();
        produtoReq3.setId(UUID.fromString("0727595e-e75a-4ad9-ae62-2fe81d4c2193"));

        List<Produto> produtos = new ArrayList<>();
        produtos.add((produtoReq1));
        produtos.add((produtoReq2));
        produtos.add((produtoReq3));

        Optional<Produto> pedidoResponse = produtoRepository.findById(UUID.fromString("0727595e-e75a-4ad9-ae62-2fe81d4c2193"));

        verify(produtoRepository, times(1)).findById(any());
        pedidoResponse.ifPresent(pedido -> assertThat(pedido).isNotNull().isEqualTo(Optional.of(produtoReq2).get()));
    }

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

    @Test
    void deveExcluirProduto() {
        var produtoReq1 = new Produto();
        var produtoReq2 = new Produto();
        var produtoReq3 = new Produto();

        List<Produto> produtos = new ArrayList<>();
        produtos.add((produtoReq1));
        produtos.add((produtoReq2));
        produtos.add((produtoReq3));

        when(produtoRepository.findAll()).thenReturn(produtos);
        doNothing().when(produtoRepository).deleteById(produtoReq2.getId());

        produtoRepository.deleteById(produtoReq2.getId());

        verify(produtoRepository, times(1)).deleteById(any());
    }

}
