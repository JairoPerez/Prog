package Practica3Ev;
import Practica3Ev.clases.Administrador;
import Practica3Ev.clases.Usuario;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Gestor gestor_eventos = new Gestor();
        Usuario miasistente = null;
        Scanner sc = new Scanner(System.in);
        String opcion;
        String opcionAdmin;
        String opcionEvento;



        do {
            System.out.println("""
                     |------------------------|
                    |# DELECTARE MULTIEVENTOS #|
                     |------------------------|
                     |   1. Login             |
                     |   2. Registrarse       |
                     |   3. Salir             |
                     --------------------------
                       Seleccione una opción:
                         """);

            opcion = sc.nextLine();

            switch (opcion) {
                case "1":
                    miasistente = gestor_eventos.login();
                    break;
                case "2":
                    miasistente = gestor_eventos.registro();
                    break;
                case "3":
                    System.out.println("Saliendo...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Introduzca una opción válida.");
            }
        } while (miasistente == null);


        if (miasistente instanceof Administrador) {
            do {
                System.out.println("MENÚ PARA ADMINISTRADORES DE DELECTARE: ");
                System.out.println("1. Gestionar eventos: ");
                System.out.println("2. Gestionar usuarios: ");
                System.out.println("3. Salir");

                opcionAdmin = sc.nextLine();

                if (opcionAdmin.equals("1")) {
                    System.out.println("Menú para gestionar los eventos.");
                } else if (opcionAdmin.equals("2")) {
                    System.out.println("Menú para gestionar los usuarios: ");
                } else if (opcionAdmin.equals("3")) {
                    System.out.println("Saliendo...");
                }

            } while (!opcionAdmin.equals("3"));
        } else {
            do {
                System.out.println("""
                        \n-----------------
                        ### DELECTARE ###
                        -----------------

                        1. Seleccionar evento.
                        2. Información reservas.
                        3. Salir.

                        Por favor, seleccione una opción:
                        """);
                opcionEvento = sc.nextLine();

                if (opcionEvento.equals("1")) {
                    gestor_eventos.mostrar_eventos(miasistente);
                } else if (opcionEvento.equals("2")) {
                    gestor_eventos.informacion_reservas(miasistente);
                } else if (opcionEvento.equals("3")) {
                    System.out.println("Saliendo...");
                } else {
                    System.out.println("Introduzca una opción válida.");
                }
            } while (!opcionEvento.equals("3"));
        }
    }
}