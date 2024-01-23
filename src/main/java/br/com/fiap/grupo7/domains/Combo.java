package br.com.fiap.grupo7.domains;

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
@Table(name = "combo")
public class Combo {

    @Id
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid")
    private UUID id;

    private String nome;

    private String descricao;

    private Double preco;

    @OneToMany(targetEntity = Produto.class, cascade = CascadeType.ALL)
    private List<Produto> produtos;

    @OneToMany(targetEntity = Imagem.class)
    private List<Imagem> imagens;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Promocao.class)
    private Promocao promocao;

}
