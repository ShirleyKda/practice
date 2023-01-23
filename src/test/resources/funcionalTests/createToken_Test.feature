
Feature: Prueba funcional generación de Token

  @prueba
  Scenario Outline: [Happy path] Usuario crea token de autorización utilizando username y password
    Given El envia su "<username>" y "<password>" para crear token
    Then el codigo estado de la respuesta debe ser 200

    Examples:
      | username | password    |
      | admin    | password123 |