import java.util.Map;

public record Resposta(String result, String base_code, Map<String, Double> conversion_rates) {
}
