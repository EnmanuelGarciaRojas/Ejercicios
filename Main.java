package Ejercicios;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("=================================");
            System.out.println("MENU");
            System.out.println("=================================");
            System.out.println("1. Cuenta Bancaria");
            System.out.println("2. Polimorfismo con Empleados");
            System.out.println("3. Ocultamiento de atributos");
            System.out.println("4. Arrays y referencias");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opcion: ");

            while (!sc.hasNextInt()) {
                System.out.print("Ingrese un número válido: ");
                sc.next();
            }

            opcion = sc.nextInt();

            switch (opcion) {

                case 1:
                    System.out.println("\n=== Cuenta Bancaria ===");

                    CuentaBancaria cuenta = new CuentaBancaria("001", 1000);
                    cuenta.depositar(500);
                    cuenta.retirar(200);

                    System.out.println("Saldo actual: " + cuenta.getSaldo());
                    break;

                case 2:
                    System.out.println("\n=== Polimorfismo con Empleados ===");

                    Empleado e1 = new Empleado.EmpleadoFijo("Juan", 25000);
                    Empleado e2 = new Empleado.EmpleadoPorHora("Ana", 80, 200);

                    System.out.println("Salario Juan: " + e1.calcularSalario());
                    System.out.println("Salario Ana: " + e2.calcularSalario());
                    break;

                case 3:
                    System.out.println("\n=== Ocultamiento de atributos ===");

                    ClaseA obj = new ClaseB();
                    System.out.println("Valor de x: " + obj.x);
                    break;

                case 4:
                    System.out.println("\n=== Arrays y referencias ===");

                    int[] array1 = {1,2,3,4};
                    int[] array2 = array1;

                    array2[1] = 99;

                    System.out.println("Contenido del primer array:");
                    for(int n : array1){
                        System.out.println(n);
                    }
                    break;

                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 0);

        sc.close();
    }
}
