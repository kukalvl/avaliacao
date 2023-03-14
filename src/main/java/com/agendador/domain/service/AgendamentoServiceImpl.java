package com.agendador.domain.service;

import com.agendador.domain.model.Agendamento;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.time.LocalDate;

@Service
public class AgendamentoServiceImpl implements AgendamentoService {

    private Double porcentagem = 0.0;
    private Double total = 0.0;
    private Double taxaA = 3.0;
    private Double taxaB = 12.0;

    @Override
    public Agendamento calculaTaxaA(Agendamento agendamento) {
        if(agendamento.getDataAgendamento().equals(agendamento.getDataTransferencia()) == true){
            porcentagem = calculaPorcentagem(agendamento.getValorTransferencia(), 3.0);
            total = (porcentagem + taxaA) + agendamento.getValorTransferencia();
            agendamento.setValorTransferencia(total);
            agendamento.setTaxa(taxaA + porcentagem);
        } else {
            agendamento.setTaxa(00.00);
        }

        return agendamento;
    }

    @Override
    public Agendamento calculaTaxaB(Agendamento agendamento) {
        if(calcularDiffDias(agendamento.getDataTransferencia(), agendamento.getDataAgendamento()) <= 10) {
            total  = taxaB + agendamento.getValorTransferencia();
            agendamento.setValorTransferencia(total);
            agendamento.setTaxa(taxaB);
        } else {
            agendamento.setTaxa(00.00);
        }

        return agendamento;
    }

    @Override
    public Agendamento calculaTaxaC(Agendamento agendamento) {
        Long dias = calcularDiffDias(agendamento.getDataTransferencia(), agendamento.getDataAgendamento());

        if(dias > 10 && dias <=20) {
            porcentagem = calculaPorcentagem(agendamento.getValorTransferencia(), 8.2);
            total = porcentagem + agendamento.getValorTransferencia();
            agendamento.setValorTransferencia(total);
            agendamento.setTaxa(porcentagem);
        } else if(dias > 20 && dias <= 30 ) {
            porcentagem = calculaPorcentagem(agendamento.getValorTransferencia(), 6.9);
            total = porcentagem + agendamento.getValorTransferencia();
            agendamento.setValorTransferencia(total);
            agendamento.setTaxa(porcentagem);
        } else if(dias > 30 && dias <= 40) {
            porcentagem = calculaPorcentagem(agendamento.getValorTransferencia(), 4.7);
            total = porcentagem + agendamento.getValorTransferencia();
            agendamento.setValorTransferencia(total);
            agendamento.setTaxa(porcentagem);
        } else if(dias > 40) {
            porcentagem = calculaPorcentagem(agendamento.getValorTransferencia(), 1.7);
            total = porcentagem + agendamento.getValorTransferencia();
            agendamento.setValorTransferencia(total);
            agendamento.setTaxa(porcentagem);
        } else {
            agendamento.setTaxa(00.00);
        }

        return agendamento;
    }

    @Override
    public Agendamento calculaTaxaD(Agendamento agendamento) {

        if(agendamento.getValorTransferencia() <= 1000) {
            calculaTaxaA(agendamento);
        } else if(agendamento.getValorTransferencia() >= 1001 && agendamento.getValorTransferencia() <= 2000) {
            calculaTaxaB(agendamento);
        } else if(agendamento.getValorTransferencia() > 2000 ) {
            calculaTaxaC(agendamento);
        } else {
            agendamento.setTaxa(00.00);
        }

        return agendamento;
    }

    @Override
    public Agendamento calculaOperacao(Agendamento agendamento) {
        if(agendamento.getTipo().equals("A")) {
            calculaTaxaA(agendamento);
        } else if (agendamento.getTipo().equals("B")) {
            calculaTaxaB(agendamento);
        } else if (agendamento.getTipo().equals("C")) {
            calculaTaxaC(agendamento);
        } else if (agendamento.getTipo().equals("D")) {
            calculaTaxaD(agendamento);
        }

        return agendamento;
    }

    private Long calcularDiffDias(LocalDate agendamento, LocalDate transferencia) {
        Long diffDias = ChronoUnit.DAYS.between(agendamento, transferencia);
        return diffDias;
    }

    private Double calculaPorcentagem(Double valorTransferencia, Double porcentagem) {
        Double result = (porcentagem * valorTransferencia) / 100;
        return result;
    }
}
