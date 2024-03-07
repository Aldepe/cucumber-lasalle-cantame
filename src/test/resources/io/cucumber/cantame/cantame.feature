Feature: Escuchar cántame con inicio de sesión

  Background:
    Given the following users are registered:
      | Username | Password |
      | <user1>  | password1|
      | <user2>  | password2|

  Scenario Outline: Iniciar sesión y escuchar dentro del rango
    Given "<user1>" ha iniciado sesión en la aplicación
    And "<user2>" ha iniciado sesión en la aplicación
    And "<user1>" está ubicado a <distance> metros de "<user2>"
    When "<user2>" canta "<message>"
    Then "<user1>" escucha el mensaje de "<user2>"

    Examples:
      | user1    | user2    | distance | message                           |
      | Pati     | Javi     | 10       | café gratuito en mi cafetería     |
      | Marta    | Pablo    | 15       | mensaje de ejemplo                |
      | John     | Alice    | 5        | Hola, ¿cómo estás?                |
      | Usuario1 | Usuario2 | 20       | ¡Oferta especial solo hoy!        |
      | Bob      | Carol    | 8        | Planifiquemos nuestra próxima reunión |
      | Emma     | David    | 12       | Recordatorio: Reunión a las 3 PM   |
      | Sarah    | Mike     | 18       | Feliz cumpleaños, Mike!           |
      | Laura    | Chris    | 7        | ¿Alguien quiere unirse a correr mañana? |
      | Alex     | Olivia   | 25       | ¡Nuevo álbum disponible ahora!    |
      | Sophia   | Ethan    | 14       | Descuento del 50% en la tienda hoy |
      | Lily     | Jake     | 9        | ¡Descubre nuestro menú especial de la semana! |
      | Daniel   | Grace    | 10       | ¡Entradas a mitad de precio para el concierto! |
      | Mia      | Ryan     | 6        | ¡Nueva colección de moda disponible ahora! |
      | Jackson  | Sophia   | 8        | Actualización: Cambio de ubicación para la reunión |
