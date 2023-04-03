package br.edu.ifsul.cstsi.tads_carolini_b.api.publicacoes;

import br.edu.ifsul.cstsi.tads_carolini_b.api.infra.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PublicacaoService {

    @Autowired
    private PublicacaoRepository rep;

    public List<PublicacaoDTO> getPublicacoes() {
        return rep.findAll().stream().map(PublicacaoDTO::create).collect(Collectors.toList());
    }

    public PublicacaoDTO getPublicacaoById(Long id) {
        Optional<Publicacao> publicacao = rep.findById(id);
        return publicacao.map(PublicacaoDTO::create).orElseThrow(() -> new ObjectNotFoundException("Publicação não encontrado"));
    }

    public List<PublicacaoDTO> getPublicacoesByNome(String nome) {
        return rep.findByNome(nome).stream().map(PublicacaoDTO::create).collect(Collectors.toList());
    }

    public PublicacaoDTO insert(Publicacao publicacao) {
        Assert.isNull(publicacao.getId(),"Não foi possível inserir a publicação");

        return PublicacaoDTO.create(rep.save(publicacao));
    }

    public PublicacaoDTO update(Publicacao publicacao, Long id) {
        Assert.notNull(id,"Não foi possível atualizar a publicação");

        // Busca a publicação no banco de dados
        Optional<Publicacao> optional = rep.findById(id);
        if(optional.isPresent()) {
            Publicacao db = optional.get();
            // Copiar as propriedades
            db.setTitulo(publicacao.getTitulo());
            db.setTexto(publicacao.getTexto());
            System.out.println("Publicação id " + db.getId());

            // Atualiza a publi
            rep.save(db);

            return PublicacaoDTO.create(db);
        } else {
            return null;
            //throw new RuntimeException("Não foi possível atualizar o registro");
        }
    }

    public void delete(Long id) {
        rep.deleteById(id);
    }
}
