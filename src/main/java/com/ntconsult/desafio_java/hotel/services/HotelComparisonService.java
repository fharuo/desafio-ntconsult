package com.ntconsult.desafio_java.hotel.services;

import com.ntconsult.desafio_java.exceptions.NotFoundException;
import com.ntconsult.desafio_java.hotel.dtos.ComparacaoDTO;
import com.ntconsult.desafio_java.hotel.dtos.ComparacaoPrecoDTO;
import com.ntconsult.desafio_java.hotel.models.Hotel;
import com.ntconsult.desafio_java.hotel.repositories.HotelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
public class HotelComparisonService {

    private final HotelRepository hotelRepository;

    public List<ComparacaoDTO> compararHoteisPorAvaliacao(Long primeiroHotelId, Long segundoHotelId) {
        Hotel hotel1 = hotelRepository.findById(primeiroHotelId)
                .orElseThrow(() -> new NotFoundException("Não foi possível encontrar o hotel # " + primeiroHotelId));

        Hotel hotel2 = hotelRepository.findById(segundoHotelId)
                .orElseThrow(() -> new NotFoundException("Não foi possível encontrar o hotel # " + segundoHotelId));

        List<ComparacaoDTO> hoteisComparados = new ArrayList<>();

        ComparacaoDTO comparacao1 = new ComparacaoDTO();
        comparacao1.setNomeHotel(hotel1.getNome());
        comparacao1.setCriterio("Avaliação");
        comparacao1.getResultado().add(hotel1.getAvaliacaoMedia().toString());

        ComparacaoDTO comparacao2 = new ComparacaoDTO();
        comparacao2.setNomeHotel(hotel2.getNome());
        comparacao2.setCriterio("Avaliação");
        comparacao2.getResultado().add(hotel2.getAvaliacaoMedia().toString());

        hoteisComparados.add(comparacao1);
        hoteisComparados.add(comparacao2);

        return hoteisComparados;
    }

    public List<ComparacaoDTO> compararHoteisPorLocalizacao(Long primeiroHotelId, Long segundoHotelId) {
        Hotel hotel1 = hotelRepository.findById(primeiroHotelId)
                .orElseThrow(() -> new NotFoundException("Não foi possível encontrar o hotel # " + primeiroHotelId));

        Hotel hotel2 = hotelRepository.findById(segundoHotelId)
                .orElseThrow(() -> new NotFoundException("Não foi possível encontrar o hotel # " + segundoHotelId));

        List<ComparacaoDTO> hoteisComparados = new ArrayList<>();

        ComparacaoDTO comparacao1 = new ComparacaoDTO();
        comparacao1.setNomeHotel(hotel1.getNome());
        comparacao1.setCriterio("Localização");
        comparacao1.getResultado().add(hotel1.getLocalizacao());

        ComparacaoDTO comparacao2 = new ComparacaoDTO();
        comparacao2.setNomeHotel(hotel2.getNome());
        comparacao2.setCriterio("Localização");
        comparacao2.getResultado().add(hotel2.getLocalizacao());

        hoteisComparados.add(comparacao1);
        hoteisComparados.add(comparacao2);

        return hoteisComparados;
    }

    public List<ComparacaoDTO> compararHoteisPorComodidades(Long primeiroHotelId, Long segundoHotelId) {
        Hotel hotel1 = hotelRepository.findById(primeiroHotelId)
                .orElseThrow(() -> new NotFoundException("Não foi possível encontrar o hotel # " + primeiroHotelId));

        Hotel hotel2 = hotelRepository.findById(segundoHotelId)
                .orElseThrow(() -> new NotFoundException("Não foi possível encontrar o hotel # " + segundoHotelId));

        List<ComparacaoDTO> hoteisComparados = new ArrayList<>();

        ComparacaoDTO comparacao1 = new ComparacaoDTO();
        comparacao1.setNomeHotel(hotel1.getNome());
        comparacao1.setCriterio("Comodidades");
        hotel1.getComodidades().forEach(comodidade -> comparacao1.getResultado().add(comodidade));

        ComparacaoDTO comparacao2 = new ComparacaoDTO();
        comparacao2.setNomeHotel(hotel2.getNome());
        comparacao2.setCriterio("Comodidades");
        hotel2.getComodidades().forEach(comodidade -> comparacao2.getResultado().add(comodidade));

        hoteisComparados.add(comparacao1);
        hoteisComparados.add(comparacao2);

        return hoteisComparados;
    }

    public List<ComparacaoPrecoDTO> compararHoteisPorPreco(Long primeiroHotelId, Long segundoHotelId) {
        Hotel hotel1 = hotelRepository.findById(primeiroHotelId)
                .orElseThrow(() -> new NotFoundException("Não foi possível encontrar o hotel # " + primeiroHotelId));

        Hotel hotel2 = hotelRepository.findById(segundoHotelId)
                .orElseThrow(() -> new NotFoundException("Não foi possível encontrar o hotel # " + segundoHotelId));

        List<ComparacaoPrecoDTO> hoteisComparados = new ArrayList<>();

        ComparacaoPrecoDTO comparacao1 = new ComparacaoPrecoDTO();
        comparacao1.setNomeHotel(hotel1.getNome());
        comparacao1.setCriterio("Preço");
        hotel1.getQuartos().forEach(quarto -> {
            Map<String, String> avaliacaoMap = new HashMap<>();
            avaliacaoMap.put("Preço", quarto.getPrecoNoite().toString());
            avaliacaoMap.put("Capacidade", quarto.getCapacidade().toString());
            comparacao1.getResultado().add(avaliacaoMap);
        });


        ComparacaoPrecoDTO comparacao2 = new ComparacaoPrecoDTO();
        comparacao2.setNomeHotel(hotel2.getNome());
        comparacao2.setCriterio("Preço");
        hotel2.getQuartos().forEach(quarto -> {
            Map<String, String> avaliacaoMap = new HashMap<>();
            avaliacaoMap.put("Preço", quarto.getPrecoNoite().toString());
            avaliacaoMap.put("Capacidade", quarto.getCapacidade().toString());
            comparacao2.getResultado().add(avaliacaoMap);
        });

        hoteisComparados.add(comparacao1);
        hoteisComparados.add(comparacao2);

        return hoteisComparados;
    }
}