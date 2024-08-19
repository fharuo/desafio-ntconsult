package com.ntconsult.desafio_java.hotel.services;

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

    public List<Hotel> pesquisarHoteis(String localizacao, LocalDate dataCheckin, LocalDate dataCheckout, int numeroQuartos, int numeroHospedes) {
        return hotelRepository.findByCriteria(localizacao, dataCheckin, dataCheckout, numeroQuartos, numeroHospedes);
    }
}