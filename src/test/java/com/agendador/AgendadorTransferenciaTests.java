package com.agendador;

import com.agendador.domain.model.Agendamento;
import com.agendador.domain.service.AgendamentoService;
import com.agendador.domain.service.AgendamentoServiceImpl;
import com.sun.source.tree.AssertTree;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AgendadorTransferenciaTests {
    private String contaOrigem;
    private String contaDestino;
    private double valorTransferencia;
    private LocalDate dataTransferencia;
    private AgendamentoServiceImpl agendamentoService;
    private LocalDate dataAgendamento;
    private String tipo;
    private Agendamento resultado;

    @Given("Conta de origem {string} Conta de destino {string} Valor da transferência {double} Data da transferência {string}")
    public void contaDeOrigemContaDeDestinoValorDaTransferênciaDataDaTransferência(String arg0, String arg1, double arg2, String arg3) {
        contaOrigem = arg0;
        contaDestino = arg1;
        valorTransferencia = arg2;
        dataTransferencia = LocalDate.parse(arg3, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Given("Data de agendamento {string} Tipo {string}")
    public void dataDeAgendamentoTipo(String arg0, String arg1) {
        dataAgendamento = LocalDate.parse(arg0, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        tipo = arg1;
    }

    @Given("Data de agendamento {string} Tipo {string} Valor da transferência {double}")
    public void dataDeAgendamentoTipoValorDaTransferência(String arg0, String arg1, double arg2) {
        dataAgendamento = LocalDate.parse(arg0, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        tipo = arg1;
        valorTransferencia = arg2;
    }

    @When("Eu solicito agendamento")
    public void euSolicitoAgendamento() throws Exception {
        var agendamento = new Agendamento();
        agendamento.setContaOrigem(contaOrigem);
        agendamento.setContaDestino(contaDestino);
        agendamento.setValorTransferencia(valorTransferencia);
        agendamento.setDataTransferencia(dataTransferencia);
        agendamento.setDataAgendamento(dataAgendamento);
        agendamento.setTipo(tipo);
        agendamentoService = new AgendamentoServiceImpl();
        resultado = agendamentoService.calculaOperacao(agendamento);
    }

    @Then("Taxa {double}")
    public void taxa(double arg0) {
        Assert.assertEquals(resultado.getTaxa(), arg0, 0.001);
    }

}
