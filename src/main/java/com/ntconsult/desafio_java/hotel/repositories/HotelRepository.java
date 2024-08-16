package com.ntconsult.desafio_java.hotel.repositories;

import com.ntconsult.desafio_java.hotel.models.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    List<Hotel> findByLocalizacaoAndPrecoNoiteBetween(String localizacao, BigDecimal precoMin, BigDecimal precoMax);

}
