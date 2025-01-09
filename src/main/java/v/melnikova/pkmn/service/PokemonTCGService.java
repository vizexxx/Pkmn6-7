package v.melnikova.pkmn.service;

import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PokemonTCGService {
    private static final String BASE_URL = "https://api.pokemontcg.io/v2/cards";

    public String getCardImageByName(String cardName) throws Exception {
        // Формируем URL запроса
        String urlString = BASE_URL + "?q=name:" + cardName;

        // Создаем URL объект и открываем соединение
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Настроим тип запроса
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");

        // Чтение ответа
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // Парсинг JSON ответа
        JSONObject jsonResponse = new JSONObject(response.toString());
        JSONArray data = jsonResponse.getJSONArray("data");

        if (data.length() > 0) {
            // Извлекаем ссылку на изображение карты
            JSONObject card = data.getJSONObject(0);
            JSONObject images = card.getJSONObject("images");
            return images.getString("large");
        } else {
            throw new Exception("Card " + cardName + " not found.");
        }
    }

    public static void main(String[] args) {
        try {
            PokemonTCGService service = new PokemonTCGService();
            String imageUrl = service.getCardImageByName("Pikachu");
            System.out.println("Card image URL: " + imageUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
