CREATE TABLE tb_equipamentos (
    id BINARY(16) NOT NULL,
    nome VARCHAR(150) NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    patrimonio VARCHAR(50) NOT NULL,
    disponivel BOOLEAN NOT NULL,
    emprestimo_id BINARY(16),

    CONSTRAINT pk_tb_equipamentos PRIMARY KEY (id),
    CONSTRAINT uk_tb_equipamentos_patrimonio UNIQUE (patrimonio),
    INDEX idx_equipamentos_disponivel (disponivel)
);
