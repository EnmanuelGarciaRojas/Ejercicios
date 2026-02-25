import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class Main {
    public static void main(String[] args){
        long tiempoInicio = System.currentTimeMillis();

        ResultadosCompartidos resultados = new ResultadosCompartidos();

        String[] archivos = {"texto1.txt", "texto2.txt", "texto3.txt"};

        Contador[] contadores = new Contador[archivos.length];
        for (int i = 0; i < archivos.length; i++) {
            contadores[i] = new Contador(archivos[i], resultados);
            contadores[i].start();
        }

        try {
            for (Contador contador : contadores){
                contador.join();
            }
        } catch (InterruptedException e){
            System.err.println("Error esperando hilos: " + e.getMessage());
        }

        long tiempoFinal = System.currentTimeMillis();
        long tiempoProcesamiento = tiempoFinal - tiempoInicio;

        generarReporte(resultados, tiempoProcesamiento);

        System.out.println("========================================");
        System.out.println("REPORTE DE PROCESAMIENTO DE ARCHIVOS");
        System.out.println("========================================");

        Map<String, Integer> datos = resultados.getResultados();
        for (String archivo : archivos){
            if (datos.containsKey(archivo)){
                System.out.println("\nArchivo: " + archivo);
                System.out.println("Palabras encontradas: " + datos.get(archivo));
            }
        }

        System.out.println("\n----------------------------------------");
        System.out.println("Total de palabras procesadas: " + resultados.getTotalPalabras());
        System.out.println("Tiempo de procesamiento: " + tiempoProcesamiento + " ms");
        System.out.println("========================================");
    }

    private static void generarReporte(ResultadosCompartidos resultados, long tiempoProcesamiento){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("estadisticas.txt"))){
            writer.write("========================================\n");
            writer.write("REPORTE DE PROCESAMIENTO DE ARCHIVOS\n");
            writer.write("========================================\n\n");

            Map<String, Integer> datos = resultados.getResultados();
            String[] archivos = {"texto1.txt", "texto2.txt", "texto3.txt"};

            for (String archivo : archivos){
                if (datos.containsKey(archivo)){
                    writer.write("Archivo: " + archivo + "\n");
                    writer.write("Palabras encontradas: " + datos.get(archivo) + "\n\n");
                }
            }

            writer.write("----------------------------------------\n");
            writer.write("Total de palabras procesadas: " + resultados.getTotalPalabras() + "\n");
            writer.write("Tiempo de procesamiento: " + tiempoProcesamiento + " ms\n");
            writer.write("========================================\n");

            System.out.println("\nReporte guardado en: estadisticas.txt");
        } catch (IOException e) {
            System.err.println("Error al generar el reporte: " + e.getMessage());
        }
    }
}