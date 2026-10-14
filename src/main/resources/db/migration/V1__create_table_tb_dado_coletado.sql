CREATE TABLE IF NOT EXISTS tb_dado_coletado (
    dado_id BIGINT NOT NULL AUTO_INCREMENT,
    sonda_nome VARCHAR(100) NOT NULL,
    agencia_espacial VARCHAR(100) NOT NULL,
    data_coleta DATE NOT NULL,
    tipo_dado VARCHAR(50) NOT NULL,
    valor_medido DECIMAL(10, 2) NOT NULL,
    unidade_medida VARCHAR(100) NOT NULL,
    CONSTRAINT pk_tb_dado_coletado PRIMARY KEY (dado_id)
);