import java.util.Random;

public class Cajero extends Thread{
    private Boveda boveda;
    private int transacciones = 0;
    private Random random = new Random();

    public Cajero(String nombre, Boveda boveda) {
        super(nombre);
        this.boveda = boveda;
    }

    @Override
    public void run(){
        int clientes = random.nextInt(3) + 3;

        for (int i = 0; i < clientes; i++) {
            try {
                int tiempo = (random.nextInt(3) + 1) * 1000; 
                Thread.sleep(tiempo);

                double monto = random.nextInt(1501) + 500;

                if (boveda.retirar(monto)) {
                    transacciones++;
                    System.out.println(getName() + 
                        " retiro $" + monto + 
                        "| Saldo restante: $" + boveda.getSaldo());
                } else {
                    System.out.println(getName() + 
                        " intento retirar $" + monto + 
                        " pero no hay saldo suficiente.");
                }

            } catch (InterruptedException e) {
                System.out.println("Error en hilo " + getName());
            }
        }
    }

    public int getTransacciones() {
        return transacciones;
    }
}
