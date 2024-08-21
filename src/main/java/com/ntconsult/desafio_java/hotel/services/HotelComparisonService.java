package com.ntconsult.desafio_java.hotel.services;

import com.ntconsult.desafio_java.hotel.models.Hotel;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelComparisonService {

    public List<Hotel> compararHoteisPorAvaliacao(List<Hotel> hoteis) {
        return hoteis.stream()
                .sorted(Comparator.comparing(Hotel::getAvaliacaoMedia).reversed())
                .collect(Collectors.toList());
    }

    public List<Hotel> compararHoteisPorLocalizacao(List<Hotel> hoteis, String localizacao) {
        return hoteis.stream()
                .sorted(Comparator.comparing(hotel -> hotel.getLocalizacao().equalsIgnoreCase(localizacao) ? 0 : 1))
                .collect(Collectors.toList());
    }

    public List<Hotel> compararHoteisPorComodidades(List<Hotel> hoteis, List<String> comodidades) {
        return hoteis.stream()
                .filter(hotel -> hotel.getComodidades().containsAll(comodidades))
                .collect(Collectors.toList());
    }
}
