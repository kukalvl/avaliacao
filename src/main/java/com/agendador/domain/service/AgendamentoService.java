package com.agendador.domain.service;

import com.agendador.domain.model.Agendamento;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AgendamentoService {
    Agendamento calculaTaxaA(Agendamento agendamento);

    Agendamento calculaTaxaB(Agendamento agendamento);

    Agendamento calculaTaxaC(Agendamento agendamento);

    Agendamento calculaTaxaD(Agendamento agendamento);

    Agendamento calculaOperacao(Agendamento agendamento);

}