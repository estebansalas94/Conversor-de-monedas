import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import com.google.gson.Gson;

public class ClienteApiTasaCambio {
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/51ca2d4417569989c4f4600a/latest/USD";
    private static final HttpClient cliente = HttpClient.newHttpClient();

    public Map<String, Double> obtenerTasasDeCambio() {
        try {
            URI url = URI.create(API_URL);
            HttpRequest solicitud = HttpRequest.newBuilder().uri(url).build();
            HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());

            if (respuesta.statusCode() == 200) {
                RespuestaApiTasaCambio respuestaApi = new Gson().fromJson(respuesta.body(), RespuestaApiTasaCambio.class);
                return respuestaApi.getTasas();
            } else {
                System.out.println("Fallo al obtener las tasas de cambio. Código de estado HTTP: " + respuesta.statusCode());
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error al obtener las tasas de cambio: " + e.getMessage());
            return null;
        }
    }
}

