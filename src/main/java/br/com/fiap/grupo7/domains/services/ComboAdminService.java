package br.com.fiap.grupo7.domains.services;

import br.com.fiap.grupo7.adapters.input.model.AlteraComboRequest;
import br.com.fiap.grupo7.adapters.input.model.PromocaoRequest;
import br.com.fiap.grupo7.domains.Combo;
import br.com.fiap.grupo7.domains.Promocao;
import br.com.fiap.grupo7.ports.input.ComboAdminServiceInterface;
import br.com.fiap.grupo7.ports.output.ComboRepository;
import br.com.fiap.grupo7.ports.output.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ComboAdminService extends ComboClienteService implements ComboAdminServiceInterface {

    private ComboRepository comboRepository;

    public ComboAdminService(ComboRepository comboRepository) {
        super(comboRepository);
        this.comboRepository = comboRepository;
    }

    public Combo criarCombo(Combo combo) throws Exception {
         /* TODO verificar se produtos existem.*/

//        if (combo.getNome() == null) {
//            throw new Exception("O combo precisa ter nome");
//        } else if (combo.getPreco() == null) {
//            throw new Exception("O combo precisa ter preço");
//        }
//
//        var categoria = this.categoriaService.obterCategoria(combo.getCategoria().getId().toString());
//
//        if (categoria == null) {
//            throw new Exception("O combo precisa estar vinculado a uma categoria válida");
//        }

        return this.comboRepository.save(combo);
    }

    public Combo alterarCombo(String id, AlteraComboRequest request) throws Exception {

        var combo = this.obterCombo(id);

        if (combo != null) {
            Combo comboAlterado = Combo.builder()
                    .id(UUID.fromString(id))
                    .nome(request.getNome())
                    .descricao(request.getDescricao())
                    .preco(combo.getPreco()) // não altera preço
                    .imagens(request.getImagens())
                    .promocao(combo.getPromocao()) // não altera promoção
                    .build();

            return this.comboRepository.save(comboAlterado);
        } else {
            throw new Exception("Este combo não existe");
        }
    }

    public Boolean alterarPrecoDoCombo(String id, Double preco) throws Exception {
        var combo = this.obterCombo(id);

        if (combo != null) {
            combo.setPreco(preco); // altera apenas preco do produto
            this.comboRepository.save(combo);
            return true;
        } else {
            throw new Exception("Este combo não existe");
        }
    }

    public Boolean cadastrarPromocaoAoCombo(String id, PromocaoRequest request) throws Exception {
        var combo = this.obterCombo(id);

        if (combo != null) {
            var promocao = Promocao.builder()
                    .produtos(null)
                    .nome(request.getNome())
                    .descricao(request.getDescricao())
                    .preco(request.getPreco())
                    .ativa(request.getAtiva())
                    .build();

            combo.setPromocao(promocao); // altera apenas apromocao
            this.comboRepository.save(combo);
            return true;
        } else {
            throw new Exception("Este combo não existe");
        }
    }

    public Boolean removerPromocaoDoCombo(String id) throws Exception {
        var combo = this.obterCombo(id);

        if (combo != null) {
            combo.setPromocao(null); // altera apenas apromocao
            this.comboRepository.save(combo);
            return true;
        } else {
            throw new Exception("Este combo não existe");
        }
    }

    public Boolean excluirCombo(String id) {
        this.comboRepository.deleteById(UUID.fromString(id));
        return true;
    }
}
