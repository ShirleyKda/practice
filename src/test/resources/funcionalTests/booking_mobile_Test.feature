Feature: Prueba funcional para la reserva en mobil

  @pruebita
  Scenario: [Happy path] Validar se pueda realizar un reserva en mobile
    When el ingresa al app mobile
    Then el codigo de respuesta debe ser 200