Feature: Testes de produtos do Serverest

  Scenario: Criar e listar produto
    Given que eu crio um novo produto
    When eu listar os produtos
    Then o produto deve estar na lista

