package br.edu.ifsul.cstsi.tads_carolini_b.api.publicacoes;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PublicacaoRepository extends JpaRepository<Publicacao,Long> {

    List<Publicacao> findByTitulo(String titulo);
}
