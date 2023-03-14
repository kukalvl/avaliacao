package com.agendador.application.service;

import com.agendador.application.dao.AgendamentoDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AgendamentoServiceApplication {
    AgendamentoDAO Agendar(AgendamentoDAO dao);
    List<AgendamentoDAO> ListarAgendamentos();

}
