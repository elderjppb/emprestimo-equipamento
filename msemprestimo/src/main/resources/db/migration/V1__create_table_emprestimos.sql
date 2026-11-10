CREATE TABLE tb_emprestimos (
    id BINARY(16) NOT NULL,
    usuario_id BINARY(16) NOT NULL,
    equipamento_id BINARY(16) NOT NULL,
    data_emprestimo DATETIME(6) NOT NULL,
    data_devolucao DATETIME(6),
    status VARCHAR(20) NOT NULL,

    CONSTRAINT pk_tb_emprestimos PRIMARY KEY (id),
    INDEX idx_emprestimos_usuario (usuario_id),
    INDEX idx_emprestimos_equipamento_status (equipamento_id, status)
);
