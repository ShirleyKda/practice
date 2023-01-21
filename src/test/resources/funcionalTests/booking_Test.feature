Feature: Prueba funcional para la reserva

  @pruebita
  Scenario: [Happy path] Validar se pueda listar todos los IDs las reservas existentes
    When el lista todos los IDs de las reservas
    Then el codigo de respuesta debe ser 200

  @pruebita
  Scenario Outline: [Happy path] Validar se puede buscar una reserva por id especifico
    When el busca una reserva especifica "<id_reserva>"
    Then el codigo de respuesta debe ser 200
    And la busqueda debe ser exitosa

    Examples:
    |id_reserva|
    |  1       |

  @pruebita
  Scenario Outline: [Happy path] Validar se pueda realizar una nueva reserva
    Given el reserva con "<nombre>" y "<apellido>"
    And pagando la reserva con 100 soles
    And en la fecha de inicio "2018-01-01" y fin "2019-01-01"
    When el realiza la nueva reserva
    Then el codigo de respuesta debe ser 200
    And la reserva debe ser exitosa

    Examples:
      |nombre      |apellido|
      |  Nicol     | Negro  |

  @pruebita
  Scenario Outline: [Happy path] Validar se pueda actualizar una reserva
    Given el selecciona la reserva de ID "1" para actualizar
    And el reserva con "<nombre>" y "<apellido>"
    And pagando la reserva con 100 soles
    And en la fecha de inicio "2018-01-01" y fin "2019-01-01"
    When el ejecuta la actualizacion de la reserva
    Then el codigo de respuesta debe ser 200
    And la reserva se actualiza exitosamente

    Examples:
      |nombre      |apellido|
      |  Frank     | Larco  |

  @pruebita
  Scenario: [Happy path] Validar se pueda actualizar nombres y apellidos de una reserva
    Given el selecciona la reserva de ID "1" para actualizar
    And el actualiza con "Carla" y "Mantari"
    When el ejecuta la actualizacion parcial de nombres_apellidos
    Then el codigo de respuesta debe ser 200

  @pruebita
  Scenario: [Happy path] Validar se pueda actualizar monto pagado de una reserva
    Given el selecciona la reserva de ID "1" para actualizar
    And pagando la reserva con 100 soles
    When el ejecuta la actualizacion parcial de monto_pagado
    Then el codigo de respuesta debe ser 200

  @pruebita
  Scenario: [Happy path] Validar se pueda actualizar las fechas de una reserva
    Given el selecciona la reserva de ID "1" para actualizar
    And en la fecha de inicio "2018-01-01" y fin "2019-01-01"
    When el ejecuta la actualizacion parcial de fecha_reserva
    Then el codigo de respuesta debe ser 200


  @pruebita
  Scenario: [Happy path] Validar se pueda eliminar una reserva por su id
    Given el selecciona la reserva de ID "3" para eliminar
    When el ejecuta la eliminación de reserva
    Then el codigo de respuesta debe ser 201

  ##############################################
  # UNHAPPY PATH
  ##############################################

  #@pruebita
  Scenario Outline: [Unhappy path] Validar genere error por no encontrar el recurso al buscar una reserva
    When el busca una reserva especifica "<id_reserva>"
    Then el codigo de respuesta debe ser 404

    Examples:
      |id_reserva|
      |  dos    |

  @pruebita
  Scenario Outline: [Unhappy path] Validar genere error de servidor por no enviar todos los datos al crear reserva
    Given el reserva con "<nombre>" y "<apellido>"
    And en la fecha de inicio "2019-01-01" y fin "2021-01-01"
    When el realiza la nueva reserva sin el campo monto pagado
    Then el codigo de respuesta debe ser 500

    Examples:
      |nombre      |apellido|
      |  Juan     | Vilchez |

  @pruebita
  Scenario Outline: [Unhappy path] Validar genere error de metodo no permitido al actualizar reserva
    Given el selecciona la reserva de ID "uno" para actualizar
    And el reserva con "<nombre>" y "<apellido>"
    And pagando la reserva con 100 soles
    And en la fecha de inicio "2018-01-01" y fin "2019-01-01"
    When el ejecuta la actualizacion de la reserva
    Then el codigo de respuesta debe ser 405

    Examples:
      |nombre      |apellido|
      |  Frank     | Larco  |




