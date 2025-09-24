CREATE TABLE moto (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      placa VARCHAR(10) NOT NULL UNIQUE,
                      modelo VARCHAR(50),
                      usuario_id BIGINT,
                      patio_id BIGINT,
                      CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id),
                      CONSTRAINT fk_patio FOREIGN KEY (patio_id) REFERENCES patio(id)
);
