package br.edu.ifsul.cstsi.tads_carolini_b.api.publicacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/publicacoes")
public class PublicacaoController {

    @Autowired
    private PublicacaoService service;

    @GetMapping
    public ResponseEntity<List<PublicacaoDTO>> selectAll() {
        List<PublicacaoDTO> publicacoes = service.getPublicacoes();
        return ResponseEntity.ok(publicacoes);
    }

    @GetMapping("{id}")
    public ResponseEntity<PublicacaoDTO> selectById(@PathVariable("id") Long id) {
        PublicacaoDTO publicacao = service.getPublicacaoById(id);
        return ResponseEntity.ok(publicacao);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<PublicacaoDTO>> selectByNome(@PathVariable("nome") String nome) {
        List<PublicacaoDTO> publicacoes = service.getPublicacoesByNome(nome);
        return publicacoes.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(publicacoes);
    }

    @PostMapping
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<String> insert(@RequestBody Publicacao publicacao){
        PublicacaoDTO p = service.insert(publicacao);
        URI location = getUri(p.getId());
        return ResponseEntity.created(location).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<PublicacaoDTO> update(@PathVariable("id") Long id, @RequestBody Publicacao publicacao){
        publicacao.setId(id);
        PublicacaoDTO p = service.update(publicacao, id);
        return p != null ?
                ResponseEntity.ok(p) :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    //utilitário
    private URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }
}
