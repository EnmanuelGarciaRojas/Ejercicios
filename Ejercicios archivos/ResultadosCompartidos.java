import java.util.HashMap;
import java.util.Map;

public class ResultadosCompartidos{
    private Map<String, Integer> resultados;
    private int totalPalabras;

    public ResultadosCompartidos(){
        this.resultados = new HashMap<>();
        this.totalPalabras = 0;
    }

    public synchronized void agregarResultado(String archivo, int palabras){
        resultados.put(archivo, palabras);
        totalPalabras += palabras;
    }

    public synchronized Map<String, Integer> getResultados(){
        return new HashMap<>(resultados);
    }

    public synchronized int getTotalPalabras(){
        return totalPalabras;
    }
}
