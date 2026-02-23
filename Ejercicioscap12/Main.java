
public class Main {
    public static void main(String[] args) {
        Boveda boveda = new Boveda();

        Cajero c1 = new Cajero("Cajero 1 ", boveda);
        Cajero c2 = new Cajero("Cajero 2 ", boveda);
        Cajero c3 = new Cajero("Cajero 3 ", boveda);

        Thread monitor = new Thread(() -> {
            try {
                while (true) {
                    System.out.println("Saldo actual en la boveda: $" + boveda.getSaldo() + "\n");
                    Thread.sleep(2000);
                }
            } catch (InterruptedException e) {
                System.out.println("Monitor detenido.");
            }
        });

        monitor.setDaemon(true);

        c1.start();
        c2.start();
        c3.start();
        monitor.start();

        try {
            c1.join();
            c2.join();
            c3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n>>>>>Resumen de los cajeros<<<<<");
        System.out.println("El cajero 1 atendio: " + c1.getTransacciones() + " transacciones.");
        System.out.println("El cajero 2 atendio: " + c2.getTransacciones() + " transacciones.");
        System.out.println("El cajero 3 atendio: " + c3.getTransacciones() + " transacciones.");
        System.out.println("Saldo final en boveda: $" + boveda.getSaldo());
    }
}
