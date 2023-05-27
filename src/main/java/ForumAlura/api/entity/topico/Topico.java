package ForumAlura.api.entity.topico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table(name="topico")
@Entity(name="Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String mensagem;
    private String autor;
    private String curso;
    private boolean ativo;

    @Column(name = "data_do_topico")
    private LocalDate dataTopico;

    public Topico(DadosTopico dados) {

        this.ativo = true;
        this.autor = dados.autor();
        this.titulo = dados.titulo();
        this.mensagem = dados.mensagem();
        this.curso = dados.curso();
        this.dataTopico = dados.dataTopico();
    }


    public void atualizarTopico(DadosTopico dados) {

        this.autor = dados.autor();
        this.titulo = dados.titulo();
        this.mensagem = dados.mensagem();
        this.curso = dados.curso();
        this.dataTopico = dados.dataTopico();
    }

    public void desativar() {

        this.ativo = false;
    }
}
