package br.edu.ifsul.cstsi.tads_carolini_b.api.publicacoes;

import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class PublicacaoDTO {
    private Long id;
    private String titulo;
    private String texto;
    public static PublicacaoDTO create(Publicacao p){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(p, PublicacaoDTO.class);
    }
}
