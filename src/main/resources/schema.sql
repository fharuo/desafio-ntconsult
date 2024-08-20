CREATE TABLE IF NOT EXISTS hotel (
    id INT8 GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    localizacao VARCHAR(255) NOT NULL,
    preco_noite DECIMAL(10, 2) NOT NULL,
    avaliacao_media DOUBLE PRECISION,
    numero_avaliacoes INTEGER
);

CREATE TABLE IF NOT EXISTS hotel_comodidades (
    hotel_id INT8 REFERENCES hotel(id) ON DELETE CASCADE,
    comodidade VARCHAR(255) NOT NULL,
    PRIMARY KEY (hotel_id, comodidade)
);

CREATE TABLE IF NOT EXISTS quarto (
    id INT8 GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    hotel_id INT8 REFERENCES hotel(id) ON DELETE CASCADE,
    numero VARCHAR(50) NOT NULL,
    preco_noite DECIMAL(10, 2) NOT NULL,
    capacidade INT8 NOT NULL DEFAULT 1,
    disponivel BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS status_reserva (
    id INT8 GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    descricao VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS reserva (
    id INT8 GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    hotel_id INT8 REFERENCES hotel(id) ON DELETE CASCADE,
    quarto_id INT8 REFERENCES quarto(id) ON DELETE CASCADE,
    status_id INT8 REFERENCES status_reserva(id) ON DELETE SET NULL,
    data_checkin DATE NOT NULL,
    data_checkout DATE NOT NULL,
    nome_cliente VARCHAR(255) NOT NULL,
    contato_cliente VARCHAR(255) NOT NULL,
    detalhes_pagamento TEXT
);

CREATE TABLE IF NOT EXISTS tipo_notificacao (
    id INT8 GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    descricao VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS status_notificacao (
    id INT8 GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    descricao VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS notificacao (
    id INT8 GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    reserva_id INT8 REFERENCES reserva(id) ON DELETE CASCADE,
    tipo_id INT8 REFERENCES tipo_notificacao(id) ON DELETE SET NULL,
    status_id INT8 REFERENCES status_notificacao(id) ON DELETE SET NULL,
    data_envio TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
