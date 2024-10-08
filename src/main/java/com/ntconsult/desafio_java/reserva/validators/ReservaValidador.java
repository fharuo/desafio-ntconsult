package com.ntconsult.desafio_java.reserva.validators;

import com.ntconsult.desafio_java.exceptions.FieldMessage;
import com.ntconsult.desafio_java.exceptions.InvalidRequestException;
import com.ntconsult.desafio_java.exceptions.NotFoundException;
import com.ntconsult.desafio_java.hotel.repositories.HotelRepository;
import com.ntconsult.desafio_java.quarto.models.Quarto;
import com.ntconsult.desafio_java.quarto.repositories.QuartoRepository;
import com.ntconsult.desafio_java.reserva.dtos.ReservaRequestDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Component
public class ReservaValidador {

    private final QuartoRepository quartoRepository;

    private final HotelRepository hotelRepository;

    public void executar(ReservaRequestDTO dto) {
        Map<String, String> camposErros = new HashMap<>();
        boolean insercaoValida = true;

        boolean disponivel = quartoRepository.isQuartoDisponivel(dto.getQuartoId(), dto.getDataCheckin(), dto.getDataCheckout());
        if (!disponivel) {
            camposErros.put("quartoId", "Quarto não disponível");
            camposErros.put("dataCheckin", "Quarto já reservado para esta data");
            camposErros.put("dataCheckout", "Quarto já reservado para esta data");
            insercaoValida = false;
        }

        Quarto quarto = quartoRepository.findById(dto.getQuartoId())
                .orElseThrow(() -> new NotFoundException("Quarto não encontrado"));

        if (dto.getPax() > quarto.getCapacidade()) {
            camposErros.put("pax", "Quarto comporta no máximo " + quarto.getCapacidade() + " pessoas");
            insercaoValida = false;
        }

        boolean quartoExiste = quartoRepository.existsById(dto.getQuartoId());
        if (!quartoExiste) {
            camposErros.put("quartoId", "Quarto não encontrado");
            insercaoValida = false;
        }

        boolean hotelExiste = hotelRepository.existsById(dto.getHotelId());
        if (!hotelExiste) {
            camposErros.put("hotelId", "Hotel não encontrado");
            insercaoValida = false;
        }

        if (!insercaoValida) {
            InvalidRequestException exception = new InvalidRequestException("Erro ao validar os dados da reserva");

            camposErros.forEach((campo, mensagem) -> exception.getFields().add(new FieldMessage(campo, mensagem)));

            throw exception;
        }

    }
}