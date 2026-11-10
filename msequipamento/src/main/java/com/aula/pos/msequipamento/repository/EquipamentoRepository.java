package com.aula.pos.msequipamento.repository;

import com.aula.pos.msequipamento.model.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EquipamentoRepository extends JpaRepository<Equipamento, UUID> {

    boolean existsByPatrimonio(String patrimonio);

    List<Equipamento> findByDisponivelTrue();

    @Modifying(clearAutomatically = true)
    @Query("""
            UPDATE Equipamento e
               SET e.disponivel = false, e.emprestimoId = :emprestimoId
             WHERE e.id = :id AND e.disponivel = true
            """)
    int reservar(UUID id, UUID emprestimoId);

    @Modifying(clearAutomatically = true)
    @Query("""
            UPDATE Equipamento e
               SET e.disponivel = true, e.emprestimoId = null
             WHERE e.id = :id AND e.emprestimoId = :emprestimoId
            """)
    int liberar(UUID id, UUID emprestimoId);
}
