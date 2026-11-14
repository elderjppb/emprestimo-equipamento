package com.aula.pos.msemprestimo.repository;

import com.aula.pos.msemprestimo.model.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, UUID> {

    List<Emprestimo> findByUsuarioIdOrderByDataEmprestimoDesc(UUID usuarioId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("""
            UPDATE Emprestimo e
               SET e.status = com.aula.pos.msemprestimo.enums.StatusEmprestimo.DEVOLVIDO, e.dataDevolucao = :dataDevolucao
             WHERE e.id = :id AND e.status = com.aula.pos.msemprestimo.enums.StatusEmprestimo.ATIVO
            """)
    int registrarDevolucao(UUID id, LocalDateTime dataDevolucao);
}
