package com.ntconsult.desafio_java.quarto.services;

import com.ntconsult.desafio_java.quarto.models.TipoQuarto;
import com.ntconsult.desafio_java.quarto.repositories.TipoQuartoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TipoQuartoService {

    private final TipoQuartoRepository tipoQuartoRepository;

    public TipoQuarto criarTipoQuarto(TipoQuarto tipoQuarto) {
        return tipoQuartoRepository.save(tipoQuarto);
    }

    public Optional<TipoQuarto> obterTipoQuartoPorId(Long id) {
        return tipoQuartoRepository.findById(id);
    }

    public Optional<TipoQuarto> obterTipoQuartoPorDescricao(String descricao) {
        return tipoQuartoRepository.findByDescricao(descricao);
    }

    public List<TipoQuarto> listarTiposQuarto() {
        return tipoQuartoRepository.findAll();
    }

    public void deletarTipoQuarto(Long id) {
        tipoQuartoRepository.deleteById(id);
    }
}
