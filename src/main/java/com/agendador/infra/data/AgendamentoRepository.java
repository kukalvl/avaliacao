package com.agendador.infra.data;

import com.agendador.application.dao.AgendamentoDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendamentoRepository extends JpaRepository<AgendamentoDAO, String> {
}
