public class Boveda {
    private double saldo = 50000;
    
    public synchronized boolean retirar(double monto) {
        if (saldo >= monto) {
            saldo -= monto;
            return true;
        }
        return false;
    }

    public synchronized  double getSaldo(){
        return saldo;
    }
}