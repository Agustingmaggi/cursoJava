package com.example.demo.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TimeService {

    public String getHoraActual() {
        RestTemplate restTemplate = new RestTemplate();
        final String url = "http://worldtimeapi.org/api/ip";

        // Realizar la solicitud GET a la API de WorldTime
        WorldTimeApiResponse response = restTemplate.getForObject(url, WorldTimeApiResponse.class);

        // Obtener la hora actual de la respuesta
        if (response != null && response.getDatetime() != null) {
            return response.getDatetime();
        } else {
            return "No se pudo obtener la hora actual.";
        }
    }

    // Clase para mapear la respuesta de la API de WorldTime
    private static class WorldTimeApiResponse {
        private String datetime;

        public String getDatetime() {
            return datetime;
        }

        public void setDatetime(String datetime) {
            this.datetime = datetime;
        }
    }
}