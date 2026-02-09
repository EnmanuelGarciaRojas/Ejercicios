package EjerciciosEnClase;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reserva {
    private String nombreCliente;
    private Date fechaReserva;
    private int cantidadPersonas;

    public Reserva(String nombreCliente, Date fechaReserva, int cantidadPersonas){
        this.nombreCliente = nombreCliente;
        this.fechaReserva = fechaReserva;
        this.cantidadPersonas = cantidadPersonas;
    }

    public Reserva(String nombreCliente, String fechaReserva, int cantidadPersonas)throws ReservaInvalidaException {
        try{
            if(nombreCliente == null || nombreCliente.trim().isEmpty()){
                throw new ReservaInvalidaException("El nombre ingresado no es valido");
            }
            this.nombreCliente = nombreCliente.trim();
        }
        catch(Exception e){
            throw new ReservaInvalidaException(e.getMessage());
        }

        try {
            if(cantidadPersonas <= 0){
                throw new ReservaInvalidaException("La cantidad de personas debe ser mayor que cero.");
            }
            this.cantidadPersonas = cantidadPersonas;
        } catch (Exception e) {
            throw new ReservaInvalidaException(e.getMessage());
        }

         try {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            formato.setLenient(false);
            this.fechaReserva = formato.parse(fechaReserva);
        } 
        catch (ParseException e) {
            throw new ReservaInvalidaException("Formato de fecha inválido.");
        }
    }

    public String getNombreCliente(){
        return nombreCliente;
    }

    public Date getFechaReserva(){
        return fechaReserva;
    }

    public int getCantidadPersonas(){
        return cantidadPersonas;
    }

    @Override
    public String toString() {
        SimpleDateFormat formato = new SimpleDateFormat("dd 'de' MMM 'de' yyyy");
        return "Cliente: " + nombreCliente +
                "|Fecha: " + formato.format(fechaReserva) +
                "| Personas: " + cantidadPersonas;
    }
}
