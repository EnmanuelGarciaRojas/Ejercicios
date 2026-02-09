package Ejercicios;

public class Empleado {

    protected String nombre;

    public Empleado(String nombre) {
        this.nombre = nombre;
    }

    public double calcularSalario() {
        return 0;
    }

    public static class EmpleadoFijo extends Empleado {

        private double salarioMensual;

        public EmpleadoFijo(String nombre, double salarioMensual) {
            super(nombre);
            this.salarioMensual = salarioMensual;
        }

        @Override
        public double calcularSalario() {
            return salarioMensual;
        }
    }

    public static class EmpleadoPorHora extends Empleado {

        private double horasTrabajadas;
        private double pagoPorHora;

        public EmpleadoPorHora(String nombre, double horasTrabajadas, double pagoPorHora) {
            super(nombre);
            this.horasTrabajadas = horasTrabajadas;
            this.pagoPorHora = pagoPorHora;
        }

        @Override
        public double calcularSalario() {
            return horasTrabajadas * pagoPorHora;
        }
    }
}