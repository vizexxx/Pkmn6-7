package v.melnikova.pkmn.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RestClient {
    // Метод для выполнения GET запроса и получения ответа в виде строки
    public String get(String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Устанавливаем метод запроса GET
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");

        // Проверяем статус ответа
        int statusCode = connection.getResponseCode();
        if (statusCode != 200) {
            throw new Exception("Failed to connect, status code: " + statusCode);
        }

        // Чтение ответа
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // Возвращаем тело ответа как строку
        return response.toString();
    }
}
