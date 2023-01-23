Feature: Prueba funcional para la reserva en mobil

  @pruebita
  Scenario: [Happy path] Realizar una reserva para la ciudad CUSCO
    Given el busca un viaje a la ciudad de "CUSCO"
    And el selecciona las fechas de "25 January 2023" al "06 February 2023"
    And el selecciona una habitacion para dos adultos y un niño de "6 years old"
    Then el le da en buscar la reserva
    And el elige la segunda opcion listada
    And el ahora elige el cuarto