package com.melany.proyecto.dashboard;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase principal que representa el panel de control del proyecto.
 * Permite registrar y mostrar tareas relacionadas con la materia.
 */
public class Dashboard {

    private ArrayList<String> tareas;

    public Dashboard() {
        tareas = new ArrayList<>();
    }

    /**
     * Muestra el menú principal en consola.
     */
    public void mostrarMenu() {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- Bienvenida, Melany ---");
            System.out.println("1. Registrar tarea");
            System.out.println("2. Mostrar tareas");
            System.out.println("3. Salir");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.print("Escribe la tarea: ");
                    String tarea = sc.nextLine();
                    registrarTarea(tarea);
                    break;
                case 2:
                    mostrarTareas();
                    break;
                case 3:
                    System.out.println("Saliendo... ¡Hasta pronto!");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 3);
    }

    /**
     * Agrega una tarea a la lista.
     * @param tarea la tarea a registrar
     */
    public void registrarTarea(String tarea) {
        tareas.add(tarea);
        System.out.println("Tarea registrada con éxito.");
    }

    public void mostrarTareas() {
        if (tareas.isEmpty()) {
            System.out.println("No hay tareas registradas.");
        } else {
            System.out.println("--- Lista de Tareas ---");
            for (String tarea : tareas) {
                System.out.println("- " + tarea);
            }
        }
    }
}