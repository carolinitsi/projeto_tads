package br.edu.ifsul.cstsi.tads_carolini_b.api.produtos;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto,Long> {

    List<Produto> findByNome(String nome);
}
