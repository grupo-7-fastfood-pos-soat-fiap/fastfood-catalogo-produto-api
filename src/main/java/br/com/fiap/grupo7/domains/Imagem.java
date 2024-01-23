package br.com.fiap.grupo7.domains;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Imagem {

    @Id
    @GenericGenerator(name = "uuid")
    private UUID id;

    private String url;
}
