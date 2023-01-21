
Feature: Prueba funcional para verificar si la API está en funcionamiento.

  #@pruebita
  Scenario: [Happy path] Validar si la api reserva esta en funcionamiento
    When el realiza un ping al api
    Then el codigo de respuesta del api ping debe ser 201


  @pruebita
  Scenario: [Unhappy path] Validar genere error porque no se encontro el recurso a testear
    When el realiza un ping al enpoint "ping_test"
    Then el codigo de respuesta del api ping debe ser 404