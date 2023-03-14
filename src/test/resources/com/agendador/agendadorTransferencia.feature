Feature: O usuário deve poder agendar uma transferência financeira; Cada tipo de transação segue uma regra diferente para cálculo da taxa.

    Background: Valor, data da transferência, conta de origem e destino pré-definido
        Given Conta de origem "XXXXXX" Conta de destino "XXXXXX" Valor da transferência 100.00 Data da transferência "2023-03-01"

    Scenario: Tranferências no mesmo dia do agendamento tem uma taxa de $3 mais 3% do valor a ser transferido
        Given Data de agendamento "2023-03-01" Tipo "A"
        When Eu solicito agendamento
        Then Taxa 6
    
    Scenario: Tranferências até 10 dias da data de agendamento possuem uma taxa de $12
        Given Data de agendamento "2023-03-10" Tipo "B"
        When Eu solicito agendamento
        Then Taxa 12

    Scenario: Operações do tipo C tem uma taxa regressiva conforme a data de transferência, acima de 10 dias da data de agendamento 8.2%
        Given Data de agendamento "2023-03-12" Tipo "C"
        When Eu solicito agendamento
        Then Taxa 8.2

    Scenario: Operações do tipo C tem uma taxa regressiva conforme a data de transferência, acima de 20 dias da data de agendamento 6.9%
        Given Data de agendamento "2023-03-22" Tipo "C"
        When Eu solicito agendamento
        Then Taxa 6.9

    Scenario: Operações do tipo C tem uma taxa regressiva conforme a data de transferência, acima de 30 dias da data de agendamento 4.7%
        Given Data de agendamento "2023-04-02" Tipo "C"
        When Eu solicito agendamento
        Then Taxa 4.7

     Scenario: Operações do tipo C tem uma taxa regressiva conforme a data de transferência, acima de 40 dias da data de agendamento 1.7%
        Given Data de agendamento "2023-04-12" Tipo "C"
        When Eu solicito agendamento
        Then Taxa 1.7
    
    Scenario: Operações do tipo D tem a taxa igual a A, B ou C dependendo do valor da transferência (Valores até $1.000 seguem a taxação tipo A)
        Given Data de agendamento "2023-03-01" Tipo "D"
        When Eu solicito agendamento
        Then Taxa 6

    Scenario: Operações do tipo D tem a taxa igual a A, B ou C dependendo do valor da transferência (Valores de $1.001 até $2.000 seguem a taxação tipo B)
        Given Data de agendamento "2023-03-10" Tipo "D" Valor da transferência 2000.00
        When Eu solicito agendamento
        Then Taxa 12

    Scenario: Operações do tipo D tem a taxa igual a A, B ou C dependendo do valor da transferência (Valores maiores que $2.000 seguem a taxação tipo C)
        Given Data de agendamento "2023-03-12" Tipo "D" Valor da transferência 2001.00
        When Eu solicito agendamento
        Then Taxa 164.082

     #Scenario: Caso não haja taxa aplicável, lançar um alerta sobre o erro
     #    Given Data de agendamento "2023-03-02" Tipo ""
     #    When Eu solicito agendamento
     #    Then Erro "Não há taxa aplicável"