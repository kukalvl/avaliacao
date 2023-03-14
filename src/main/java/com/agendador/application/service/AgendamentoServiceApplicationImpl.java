package com.agendador.application.service;

import com.agendador.application.dao.AgendamentoDAO;
import com.agendador.domain.model.Agendamento;
import com.agendador.domain.service.AgendamentoService;
import com.agendador.infra.data.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendamentoServiceApplicationImpl implements AgendamentoServiceApplication {
    @Autowired
    private AgendamentoService agendamentoService;
    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Override
    public AgendamentoDAO Agendar(AgendamentoDAO dao){
        Agendamento agendamento = new Agendamento();
        agendamento.setContaOrigem(dao.getContaOrigem());
        agendamento.setContaDestino(dao.getContaDestino());
        agendamento.setValorTransferencia(dao.getValorTransferencia());
        agendamento.setDataTransferencia(dao.getDataTransferencia());
        agendamento.setDataAgendamento(dao.getDataAgendamento());
        agendamento.setTipo(dao.getTipo());

        agendamentoService.calculaOperacao(agendamento);
        return agendamentoRepository.save(dao);
    }

    @Override
    public List<AgendamentoDAO> ListarAgendamentos(){
        return agendamentoRepository.findAll();
    }
}
