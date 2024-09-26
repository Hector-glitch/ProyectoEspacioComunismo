import java.util.HashMap;

public class Fisic {
    // Mapa de radis dels planetes en Km (valors ficticis)
    private static final HashMap<String, Double> radis = new HashMap<>();

    static {
        radis.put("Mercuri", 2_439.7); // Radi en km
        radis.put("Venus", 6_051.8);
        radis.put("Mart", 3_389.5);
        radis.put("Júpiter", 69_911.0);
        radis.put("Saturn", 58_232.0);
        radis.put("Urà", 25_362.0);
        radis.put("Neptú", 24_622.0);
    }

    // Mètode que calcula l'àrea del planeta (suposant que és una esfera)
    public static double CalcArea(String planeta) {
        Double radi = radis.get(planeta);
        if (radi != null) {
            // Fórmula de l'àrea d'una esfera: A = 4 * π * r^2
            double area = 4 * Math.PI * Math.pow(radi, 2);
            return area; // Retornar l'àrea calculada
        } else {
            System.out.println("Planeta no trobat.");
            return -1;
        }
    }

    public static double CalcTemps(String planeta) {
        HashMap<String, Double> distancias = new HashMap<>();
        distancias.put("Mercuri", 101_000_000.0);
        distancias.put("Venus", 40_000_000.0);
        distancias.put("Mart", 58_000_000.0);
        distancias.put("Júpiter", 594_000_000.0);
        distancias.put("Saturn", 1_207_000_000.0);
        distancias.put("Urà", 2_601_000_000.0);
        distancias.put("Neptú", 4_306_000_000.0);

        Double distancia = distancias.get(planeta);
        if (distancia != null) {
            // Calcular el temps en anys
            double temps = (distancia * 2) / 147_000_000.0;
            return temps; // Retornar el temps calculat
        } else {
            System.out.println("Planeta no trobat.");
            return -1; // Retornar -1 si el planeta no és trobat
        }
    }
    public static double CalcCostRecorre(double area) {
        if (area > 0) {
            // Fórmula: cost = àrea * 80 m³/km² * 10.000 €/m³
            double cost = area * 80 * 10_000;
            return cost;
        } else {
            System.out.println("Àrea incorrecta.");
            return -1;
        }
    }

    public static String CompararCostos() {
        HashMap<String, Double> costos = new HashMap<>();

        // Calcular el cost d'investigar per a cada planeta
        costos.put("Mercuri", CalcCostRecorre(CalcArea("Mercuri")));
        costos.put("Venus", CalcCostRecorre(CalcArea("Venus")));
        costos.put("Mart", CalcCostRecorre(CalcArea("Mart")));
        costos.put("Júpiter", CalcCostRecorre(CalcArea("Júpiter")));
        costos.put("Saturn", CalcCostRecorre(CalcArea("Saturn")));
        costos.put("Urà", CalcCostRecorre(CalcArea("Urà")));
        costos.put("Neptú", CalcCostRecorre(CalcArea("Neptú")));

        // Determinar el planeta amb el cost més baix
        String planetaEconomico = null;
        double minCost = Double.MAX_VALUE;

        for (String planeta : costos.keySet()) {
            double cost = costos.get(planeta);
            if (cost < minCost) {
                minCost = cost;
                planetaEconomico = planeta;
            }
        }

        return planetaEconomico;
    }

}
