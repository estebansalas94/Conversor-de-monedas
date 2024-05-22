import java.util.Map;
import java.util.Scanner;

public class ConvertidorMonedas {
    private ClienteApiTasaCambio apiCliente;

    public ConvertidorMonedas(ClienteApiTasaCambio apiCliente) {
        this.apiCliente = apiCliente;
    }

    public void ejecutar() {
        Map<String, Double> tasas = apiCliente.obtenerTasasDeCambio();
        if (tasas != null) {
            mostrarMenu(tasas);
        } else {
            System.out.println("Fallo al obtener las tasas de cambio.");
        }
    }

    private void mostrarMenu(Map<String, Double> tasas) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Convertidor de Monedas");
            System.out.println("1. ARS - Peso argentino");
            System.out.println("2. BOB - Boliviano boliviano");
            System.out.println("3. BRL - Real brasileño");
            System.out.println("4. CLP - Peso chileno");
            System.out.println("5. COP - Peso colombiano");
            System.out.println("6. USD - Dólar estadounidense");
            System.out.println("7. Salir");
            System.out.print("Elija la moneda a la que desea convertir (1-6): ");
            int opcion = scanner.nextInt();
            if (opcion == 7) break;

            System.out.print("Ingrese la cantidad en USD: ");
            double cantidad = scanner.nextDouble();

            String codigoMoneda = obtenerCodigoMoneda(opcion);
            if (codigoMoneda != null) {
                Double tasa = tasas.get(codigoMoneda);
                if (tasa != null) {
                    double cantidadConvertida = convertirMoneda(cantidad, tasa);
                    System.out.printf("Cantidad convertida: %.2f %s%n", cantidadConvertida, codigoMoneda);
                } else {
                    System.out.println("No se encontró la tasa para " + codigoMoneda + ".");
                }
            } else {
                System.out.println("Opción inválida. Por favor, intente de nuevo.");
            }
        }
        scanner.close();
    }

    private String obtenerCodigoMoneda(int opcion) {
        return switch (opcion) {
            case 1 -> "ARS";
            case 2 -> "BOB";
            case 3 -> "BRL";
            case 4 -> "CLP";
            case 5 -> "COP";
            case 6 -> "USD";
            default -> null;
        };
    }

    private double convertirMoneda(double cantidad, double tasa) {
        return cantidad * tasa;
    }

    public static void main(String[] args) {
        ClienteApiTasaCambio apiCliente = new ClienteApiTasaCambio();
        ConvertidorMonedas convertidor = new ConvertidorMonedas(apiCliente);
        convertidor.ejecutar();
    }
}

