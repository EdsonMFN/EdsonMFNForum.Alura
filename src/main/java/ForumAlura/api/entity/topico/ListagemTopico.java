package ForumAlura.api.entity.topico;

import java.time.LocalDate;

public record ListagemTopico(Long id, String titulo, String mensagem, String autor, String curso, LocalDate dataTopico) {

    public ListagemTopico(Topico topico){

        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getAutor(), topico.getCurso(), topico.getDataTopico());
    }
}
