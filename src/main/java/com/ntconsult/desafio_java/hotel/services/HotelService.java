package com.ntconsult.desafio_java.hotel.services;

import com.ntconsult.desafio_java.hotel.dtos.HotelDTO;
import com.ntconsult.desafio_java.hotel.models.Hotel;
import com.ntconsult.desafio_java.hotel.repositories.HotelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;

    public List<HotelDTO> listarHoteis() {
        List<Hotel> hoteis = hotelRepository.findAll();

        return hoteis.stream().map(HotelDTO::new).toList();
    }

    public List<HotelDTO> pesquisarHoteis(String localizacao, LocalDate dataCheckin, LocalDate dataCheckout, int numeroQuartos, int numeroHospedes) {
        List<Hotel> hoteis = hotelRepository.findByCriteria(localizacao, dataCheckin, dataCheckout, numeroQuartos, numeroHospedes);

        return hoteis.stream().map(HotelDTO::new).toList();
    }
}