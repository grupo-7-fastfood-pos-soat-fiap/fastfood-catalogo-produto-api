package br.com.fiap.grupo7.domains;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "produto")
public class Produto {

    @Id
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid")
    private UUID id;

    private String nome;

    private Double preco;

    private String descricao;

    @JsonProperty("categoria")
    @ManyToOne
    private Categoria categoria;

    @JsonProperty("quantidade_disponivel_estoque")
    private Integer quantidadeDisponivelEstoque;

    @OneToMany(targetEntity = Imagem.class)
    private List<Imagem> imagens;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Combo.class)
    @JoinColumn(name = "combo_id")
    private Combo combo;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Promocao.class)
    private Promocao promocao;
}
