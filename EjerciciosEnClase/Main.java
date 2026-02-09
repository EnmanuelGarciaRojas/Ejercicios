package EjerciciosEnClase;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Reserva> reservas = new ArrayList<>();

        int opcion;

        do {
            System.out.println(">>>>>>>>Menu de Reservas<<<<<<<<");
            System.out.println("1. Registrar una reserva");
            System.out.println("2. Mostrar reservas");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {

                case 1:
                    try {
                        System.out.print("Nombre del cliente: ");
                        String nombre = scanner.nextLine();

                        System.out.print("Fecha (dd/MM/yyyy): ");
                        String fecha = scanner.nextLine();

                        System.out.print("Cantidad de personas: ");
                        int cantidad = Integer.parseInt(scanner.nextLine());

                        Reserva nuevaReserva = new Reserva(nombre, fecha, cantidad);
                        reservas.add(nuevaReserva);

                        System.out.println("Reserva registrada correctamente.");

                    } catch (ReservaInvalidaException e) {
                        System.out.println("Error: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Entrada inválida.");
                    }
                    break;

                case 2:
                    if (reservas.isEmpty()) {
                        System.out.println("No hay reservas registradas.");
                    } else {
                        for (Reserva r : reservas) {
                            System.out.println(r);
                        }
                    }
                    break;

                case 3:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }

        } 
        while (opcion != 3);

        scanner.close();
    }
}
