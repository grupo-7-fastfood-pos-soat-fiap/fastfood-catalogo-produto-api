package br.com.fiap.grupo7.domains.services;

import br.com.fiap.grupo7.domains.Categoria;
import br.com.fiap.grupo7.ports.output.CategoriaRepository;
import br.com.fiap.grupo7.ports.output.ProdutoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertNull;

class CategoriaServiceTest {

    private CategoriaService categoriaService;

    @Mock
    private CategoriaRepository categoriaRepository;

    AutoCloseable openMocks;

    private final UUID ID_CLIENTE = UUID.fromString("0727595e-e75a-4ad9-ae62-2fe81d4c2193");
    private final UUID ID_PEDIDO = UUID.fromString("914b7e70-3e0c-4bd7-89c3-595b0db89205");

    @BeforeEach
    void setup() {
        openMocks = MockitoAnnotations.openMocks(this);
        categoriaService = new CategoriaService(this.categoriaRepository);
    }

    @AfterEach()
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void deveObterCategoria() {
        UUID id = UUID.randomUUID();
        when(categoriaRepository.findById(id)).thenReturn(Optional.empty());

        categoriaService.obterCategoria(id.toString());

        //assertNull(result);
    }
//
//    @Test
//    void deveCriarPedidoSeClientePossuirApenasId_e_outrosDadosForemNulls() throws Exception {
//
//        Cliente cliente = new Cliente().builder()
//                .nome(null)
//                .email(null)
//                .cpf(null)
//                // .historicoPedidos(null)
//                .id(ID_CLIENTE)
//                .build();;
//
//        var pedido = this.criarPerdido(cliente);
//
//        when(produtoRepository.save(any(Pedido.class))).thenReturn(pedido);
//
//        var resultado = produtoAdminService.criarPedido(pedido);
//
//        verify(produtoRepository, times(1)).save(pedido);
//        assertThat(resultado).isEqualTo(pedido);
//    }
//
//    @Test
//    void deveCriarPedidoComSucesso_seCpfVazio() throws Exception {
//
//        Cliente cliente = new Cliente().builder()
//                .nome("")
//                .email("")
//                .cpf("")
//                .id(ID_CLIENTE)
//                .build();;
//
//        var pedido = this.criarPerdido(cliente);
//
//        when(produtoRepository.save(any(Pedido.class))).thenReturn(pedido);
//
//        var resultado = produtoAdminService.criarPedido(pedido);
//
//        verify(produtoRepository, times(1)).save(pedido);
//        assertThat(resultado).isEqualTo(pedido);
//        assertThat(resultado.getCliente().getCpf().isBlank()).isTrue();
//    }
//
//    @Test
//    void deveCriarPedidoComSucesso_seCpfNulo() throws Exception {
//
//        Cliente cliente = new Cliente().builder()
//                .nome("")
//                .email("")
//                .cpf(null)
//                .id(ID_CLIENTE)
//                .build();;
//
//        var pedido = this.criarPerdido(cliente);
//
//        when(produtoRepository.save(any(Pedido.class))).thenReturn(pedido);
//
//        var resultado = produtoAdminService.criarPedido(pedido);
//
//        verify(produtoRepository, times(1)).save(pedido);
//        assertThat(resultado).isEqualTo(pedido);
//        assertThat(resultado.getCliente().getCpf()).isNull();
//    }
//
//    @Test
//    void naoDeveCriarPedidoSeNaoExistirNenhumIdentificadorDoCliente_id_ou_cpf() {
//
//        Cliente cliente = new Cliente().builder()
//                .nome("")
//                .email("")
//                .cpf(null)
//                .id(null)
//                .build();;
//
//        var pedido = this.criarPerdido(cliente);
//
//        when(produtoRepository.save(any(Pedido.class))).thenReturn(pedido);
//
//        verify(produtoRepository, times(0)).save(pedido);
//    }
//
//    @Test
//    void naoDeveCriarPedidoSeClientePossuirCpfMasNaoPossuirId() {
//
//        Cliente cliente = new Cliente().builder()
//                .nome("")
//                .email("")
//                .cpf("4780496820")
//                .id(null)
//                .build();;
//
//        var pedido = this.criarPerdido(cliente);
//
//        when(produtoRepository.save(any(Pedido.class))).thenReturn(pedido);
//
//        verify(produtoRepository, times(0)).save(pedido);
//    }
//
//    @Test
//    void deveSerPermitidoCriarPedidoContendoMaisDeUmCombo() throws Exception {
//        Cliente cliente = new Cliente().builder()
//                .nome("")
//                .email("")
//                .cpf("4780496820")
//                .id(ID_CLIENTE)
//                .build();;
//
//        var pedido = this.criarPerdido(cliente);
//
//        List<Combo> combos = new ArrayList<>();
//        Combo combo1 = new Combo();
//        Combo combo2 = new Combo();
//        combos.add(combo1);
//        combos.add(combo2);
//
//        pedido.setCombos(combos);
//
//        when(produtoRepository.save(any(Pedido.class))).thenReturn(pedido);
//
//        var resultado = produtoAdminService.criarPedido(pedido);
//
//        verify(produtoRepository, times(1)).save(pedido);
//        assertThat(resultado).isEqualTo(pedido);
//        assertThat(resultado.getCombos().size()).isGreaterThanOrEqualTo(1);
//    }
//
//    @Test
//    void deveSerPermitidoCriarPedidoComComboContendoMaisDeUmProduto() throws Exception {
//        Cliente cliente = new Cliente().builder()
//                .nome("")
//                .email("")
//                .cpf("4780496820")
//                .id(ID_CLIENTE)
//                .build();;
//
//        var pedido = this.criarPerdido(cliente);
//        List<Produto> produtos = new ArrayList<>();
//        var produto1 = new Produto();
//        var produto2 = new Produto();
//
//        produtos.add(produto1);
//        produtos.add(produto2);
//
//        List<Combo> combos = new ArrayList<>();
//        Combo combo = new Combo();
//        combo.setProdutos(produtos);
//        combos.add(combo);
//
//        pedido.setCombos(combos);
//
//        when(produtoRepository.save(any(Pedido.class))).thenReturn(pedido);
//
//        var resultado = produtoAdminService.criarPedido(pedido);
//
//        verify(produtoRepository, times(1)).save(pedido);
//        assertThat(resultado).isEqualTo(pedido);
//        assertThat(resultado.getCombos().size()).isGreaterThanOrEqualTo(1);
//        assertThat(resultado.getCombos().get(0).getProdutos().size()).isGreaterThanOrEqualTo(1);
//    }
//
//    @Test
//    void naoDeveSerPermitidoCriarPedidoSeNaoTiverNoMinimoUmCombo() {
//        Cliente cliente = new Cliente().builder()
//                .nome("")
//                .email("")
//                .cpf("4780496820")
//                .id(ID_CLIENTE)
//                .build();;
//
//        var pedido = this.criarPerdido(cliente);
//
//        pedido.setCombos(new ArrayList<>());
//
//        when(produtoRepository.save(any(Pedido.class))).thenReturn(pedido);
//
//        assertThatThrownBy(() -> {
//            produtoAdminService.criarPedido(pedido);
//        }).isInstanceOf(Exception.class);
//
//        verify(produtoRepository, times(0)).save(pedido);
//
//    }
//
//    @Test()
//    void naoDeveSerPermitidoCriarPedidoSeComboNaoTiverProduto() throws Exception {
//        Cliente cliente = new Cliente().builder()
//                .nome("")
//                .email("")
//                .cpf("4780496820")
//                .id(ID_CLIENTE)
//                .build();;
//
//        var pedido = this.criarPerdido(cliente);
//
//        List<Combo> combos = new ArrayList<>();
//        Combo combo1 = new Combo();
//        combo1.setProdutos(new ArrayList<>());
//        combos.add(combo1);
//
//        pedido.setCombos(combos);
//
//        when(produtoRepository.save(any(Pedido.class))).thenReturn(pedido);
//
//        assertThatThrownBy(() -> produtoAdminService.criarPedido(pedido)).isInstanceOf(Exception.class);
//        verify(produtoRepository, times(0)).save(pedido);
//    }

    @Test
    void deveRemoverProdutosDoPedido() {
    fail("deveRemoverProdutosDoPedido");
    }

    @Test
    void deveEditarPedido() {
        fail("deveEditarPedido");
    }

    @Test
    void deveRemoverPedido() {
        fail("deveRemoverPedido");
    }

    @Test
    void deveBuscarPedidoPorIdentificador() {
        fail("deveBuscarPedidoPorIdentificador");
    }

    @Test
    void deveBuscarTodosPedidosDaUltimaHora() {
        fail("deveBuscarTodosPedidos");
    }

    @Test
    void deveBuscarTodosPedidosDoDia() {
        fail("deveBuscarTodosPedidos");
    }

    @Test
    void deveBuscarTodosPedidosDaUltimaHoraPorStatusDeAndamento() {
        fail("deveBuscarTodosPedidosDaUltimaHoraPorStatusDeAndamento");
    }

    @Test
    void deveSomarTotalDoValorDoPedidoDeAcordoComOsProdutos() {
        fail("deveSomarTotalDoValorDoPedidoDeAcordoComOsProdutos");
    }

    @Test
    void deveBuscarPedidoDoClientePorStatusDeAndamento() {
        fail("deveBuscarPedidoDoClientePorStatusDeAndamento");
    }


    @Test
    void deveBuscarHistoricoDePedidosDoClienteSelecionado() {
        fail("deveBuscarHistoricoDePedidosDoClienteSelecionado");
    }

//    private Pedido criarPerdido(Cliente cliente) {
//        Combo combo = new Combo();
//        List<Combo> combos = new ArrayList<>();
//        combos.add(combo);
//
//        List<HistoricoAndamento> listaDeHistoricoAndamentos = new ArrayList<>();
//        listaDeHistoricoAndamentos.add(HistoricoAndamento.builder().id(0).descricao("Realizado").build());
//
//        return new Pedido().toBuilder()
//                .id(ID_PEDIDO)
//                .isPedidoPago(false)
//                .valorTotal(10.20)
//                .codigoAcompanhamento("")
//                .cliente(this.criarCliente(cliente))
//                .dataCriacao(LocalDateTime.now())
//                .combos(combos)
//                .historicoAndamentos(listaDeHistoricoAndamentos)
//                .build();
//    }
//
//    private Cliente criarCliente(Cliente cliente) {
//        return new Cliente().builder()
//                .nome(cliente.getNome())
//                .email(cliente.getEmail())
//                .cpf(cliente.getCpf())
//                .id(cliente.getId())
//                .build();
//    }

}


