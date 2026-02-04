package Ejercicios;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("===============================================");
            System.out.println("Menu");
            System.out.println("===============================================");
            System.out.println("1. Cuenta Bancaria");
            System.out.println("2. Polimorfismo con Empleados");
            System.out.println("3. Ocultamiento de atributos");
            System.out.println("4. Arrays y referencias");
            System.out.println("11. Salir");
            System.out.println("===============================================");
            System.out.print("Seleccione una opcion: ");

            while (!sc.hasNextInt()) {
                System.out.print("Error: debe ingresar un número válido: ");
                sc.next();
            }

            opcion = sc.nextInt();
            sc.nextLine();

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

                    Empleado e1 = new EmpleadoFijo("Juan", 25000);
                    Empleado e2 = new EmpleadoPorHora("Ana", 80, 200);

                    System.out.println("Salario Juan: " + e1.calcularSalario());
                    System.out.println("Salario Ana: " + e2.calcularSalario());
                    break;

                case 3:
                    System.out.println("\n=== Ocultamiento de atributos ===");

                    Persona p = new Persona("Carlos", 30);

                    System.out.println("Nombre: " + p.getNombre());
                    System.out.println("Edad: " + p.getEdad());
                    break;

                case 4:
                    System.out.println("\n=== Arrays y referencias ===");

                    Persona[] personas = new Persona[2];

                    personas[0] = new Persona("Luis", 20);
                    personas[1] = new Persona("Maria", 25);

                    for (Persona per : personas) {
                        System.out.println(per.getNombre() + " - " + per.getEdad());
                    }
                    break;

                case 11:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 11);

        sc.close();
    }
}

