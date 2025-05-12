import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Requisicao {
    private final String apiKey;
    private final HttpClient client;
    private final Gson gson;

    public Requisicao(String apiKey) {
        this.apiKey = apiKey;
        this.client = HttpClient.newHttpClient();
        this.gson = new Gson();
    }

    public Resposta getConversao(String moedaBase) throws Exception {
        String url = "https://v6.exchangerate-api.com/v6/"+ apiKey +"/latest/" + moedaBase;
        URI endereco = URI.create(url);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(endereco)
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return gson.fromJson(response.body(), Resposta.class);
    }
}
