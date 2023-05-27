package ForumAlura.api.controller;

import ForumAlura.api.repository.TopicoRpository;
import ForumAlura.api.entity.topico.DadosTopico;
import ForumAlura.api.entity.topico.ListagemTopico;
import ForumAlura.api.entity.topico.Topico;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topico")
public class TopicoController {

    @Autowired
    private TopicoRpository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody  @Valid DadosTopico dados, UriComponentsBuilder uriBuilder){
        var topico = new Topico(dados);
        repository.save(topico);

        var uri = uriBuilder.path("/topico/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new ListagemTopico(topico));
    }

    @GetMapping
    public ResponseEntity<Page<ListagemTopico>> listar(@PageableDefault(size = 10, sort = {"dataTopico"}) Pageable paginacao){

        var page = repository.findAllByAtivoTrue(paginacao).map(ListagemTopico::new);
        return ResponseEntity.ok(page);
    }
    @GetMapping("/{id}")
    public ResponseEntity buscar(@PathVariable Long id){
        var busca = repository.getReferenceById(id);
        return ResponseEntity.ok(new ListagemTopico(busca));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosTopico dados){
        var topico = repository.getReferenceById(dados.id());
        topico.atualizarTopico(dados);

        return ResponseEntity.ok(new ListagemTopico(topico));
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        var topico = repository.getReferenceById(id);
        topico.desativar();

        return ResponseEntity.noContent().build();

    }
}
