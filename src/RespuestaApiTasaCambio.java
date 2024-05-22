import java.util.Map;

public class RespuestaApiTasaCambio {
    private String result;
    private Map<String, Double> conversion_rates;

    public String getResult() {
        return result;
    }

    public Map<String, Double> getTasas() {
        return conversion_rates;
    }
}

