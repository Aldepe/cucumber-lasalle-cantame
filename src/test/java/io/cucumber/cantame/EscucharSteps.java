package io.cucumber.cantame;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EscucharSteps {

    private Map<String, String> registeredUsers = new HashMap<>();
    private Map<String, Boolean> userSessions = new HashMap<>();
    private Map<String, Integer> userLocations = new HashMap<>();
    private Map<String, String> userMessages = new HashMap<>();

    @Given("the following users are registered:")
    public void the_following_users_are_registered(DataTable dataTable) {
        List<Map<String, String>> users = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> user : users) {
            String username = user.get("Username");
            String password = user.get("Password");
            registeredUsers.put(username, password);
        }
    }

    @Given("{string} ha iniciado sesión en la aplicación")
    public void ha_iniciado_sesión_en_la_aplicación(String username) {
        boolean hasLoggedIn = Math.random() < 0.5;
        userSessions.put(username, hasLoggedIn);
    }

    @Given("{string} está ubicado a {int} metros de {string}")
    public void está_ubicado_a_metros_de(String user1, Integer distance, String user2) {
        userLocations.put(user1, distance);
        userLocations.put(user2, 0);
    }

    @When("{string} canta {string}")
    public void canta(String singer, String message) {
        userMessages.put(singer, message);
    }

    @Then("{string} escucha el mensaje de {string}")
    public void escucha_el_mensaje_de(String listener, String singer) {
        if (userLocations.containsKey(singer) && userLocations.containsKey(listener)) {
            Integer singerLocation = userLocations.get(singer);
            Integer listenerLocation = userLocations.get(listener);

            if (singerLocation != null && listenerLocation != null) {
                String message = userMessages.get(singer);
                Boolean singerHasLoggedIn = userSessions.get(singer);
                Boolean listenerHasLoggedIn = userSessions.get(listener);

                if (message != null) {
                    if (singerHasLoggedIn != null && listenerHasLoggedIn != null && singerHasLoggedIn && listenerHasLoggedIn) {
                        if (Math.abs(listenerLocation.intValue() - singerLocation.intValue()) <= 10) {
                            System.out.println(listener + " escucha el mensaje de " + singer + ": " + message);
                        } else {
                            System.out.println(listener + " no puede escuchar el mensaje de " + singer + " porque están demasiado lejos.");
                        }
                    } else {
                        System.out.println("Usuario no ha iniciado sesión.");
                    }
                } else {
                    System.out.println(singer + " no ha cantado ningún mensaje.");
                }
            } else {
                System.out.println("Ubicación no disponible para " + singer + " o " + listener);
            }
        } else {
            System.out.println("Usuario no encontrado en la ubicación");
        }
    }

}
