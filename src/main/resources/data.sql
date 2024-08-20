INSERT INTO status_reserva (id, descricao) VALUES
(1, 'PENDENTE'),
(2, 'CONFIRMADA'),
(3, 'CANCELADA'),
(4, 'CHECKED_IN'),
(5, 'CHECKED_OUT');

INSERT INTO status_notificacao (id, descricao) VALUES
(1, 'ENVIADA'),
(2, 'PENDENTE');

INSERT INTO tipo_notificacao (id, descricao) VALUES
(1, 'CONFIRMACAO_RESERVA'),
(2, 'CANCELAMENTO_RESERVA'),
(3, 'CHECKIN_REALIZADO'),
(4, 'CHECKOUT_REALIZADO');

INSERT INTO hotel (id, nome, localizacao, preco_noite, avaliacao_media, numero_avaliacoes) VALUES
(1, 'Hotel Luxo', 'Avenida Paulista, São Paulo', 500.00, 4.5, 150),
(2, 'Hotel Simples', 'Centro, Rio de Janeiro', 200.00, 3.8, 75),
(3, 'Hotel Conforto', 'Copacabana, Rio de Janeiro', 350.00, 4.2, 90);

INSERT INTO hotel_comodidades (hotel_id, comodidade) VALUES
(1, 'Wi-Fi gratuito'),
(1, 'Piscina'),
(1, 'Estacionamento'),
(2, 'Wi-Fi gratuito'),
(2, 'Café da manhã'),
(3, 'Wi-Fi gratuito'),
(3, 'Vista para o mar'),
(3, 'Ar-condicionado');

INSERT INTO tipo_quarto (id, descricao) VALUES
(1, 'Single'),
(2, 'Double'),
(3, 'Suite');

INSERT INTO quarto (id, hotel_id, numero, tipo_id, preco_noite, disponivel) VALUES
(1, 1, '101', 1, 500.00, TRUE),
(2, 1, '102', 2, 600.00, TRUE),
(3, 2, '201', 1, 200.00, TRUE),
(4, 2, '202', 2, 250.00, TRUE),
(5, 3, '301', 3, 700.00, TRUE);