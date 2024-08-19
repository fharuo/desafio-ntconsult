package com.ntconsult.desafio_java.quarto.services;

import com.ntconsult.desafio_java.quarto.models.Quarto;
import com.ntconsult.desafio_java.quarto.repositories.QuartoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class QuartoService {

    private final QuartoRepository quartoRepository;

    public Quarto criarQuarto(Quarto quarto) {
        return quartoRepository.save(quarto);
    }

    public Optional<Quarto> obterQuartoPorId(Long id) {
        return quartoRepository.findById(id);
    }

    public List<Quarto> listarQuartosPorHotel(Long hotelId) {
        return quartoRepository.findByHotelId(hotelId);
    }

    public void deletarQuarto(Long id) {
        quartoRepository.deleteById(id);
    }
}
