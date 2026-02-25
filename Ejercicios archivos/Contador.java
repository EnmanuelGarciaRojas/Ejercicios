import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Contador extends Thread{
    private String nombreArchivo;
    private ResultadosCompartidos resultados;

    public Contador(String nombreArchivo, ResultadosCompartidos resultados){
        this.nombreArchivo = nombreArchivo;
        this.resultados = resultados;
    }

    @Override
    public void run(){
        try {
            int palabras = contarPalabras(nombreArchivo);
            resultados.agregarResultado(nombreArchivo, palabras);
        } catch (IOException e) {
            System.err.println("Error al procesar " + nombreArchivo + ": " + e.getMessage());
        }
    }

    private int contarPalabras(String nombreArchivo) throws IOException{
        int contador = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))){
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    String[] palabras = linea.trim().split("\\s+");
                    contador += palabras.length;
                }
            }
        }
        return contador;
    }
}
