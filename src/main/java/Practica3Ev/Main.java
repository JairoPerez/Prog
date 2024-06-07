package Practica3Ev;

import Practica3Ev.Excepciones.Myexception;
import Practica3Ev.clases.Administrador;
import Practica3Ev.clases.Usuario;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Gestor gestor_eventos = new Gestor();
        Usuario miasistente = null;
        Scanner sc = new Scanner(System.in);
        int opcionAdmin = 0;
        int opcionEvento = 0;


        do {
            try {
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

                int opcion = sc.nextInt();

                switch (opcion) {
                    case 1:

                        miasistente = gestor_eventos.login();
                        break;
                    case 2:
                        miasistente = gestor_eventos.registro();
                        break;
                    case 3:
                        System.out.println("Saliendo...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Introduzca una opción válida.");
                }

            } catch (InputMismatchException ex) {
                System.out.println(ex.getMessage());
                sc.next();
            }
        } while (miasistente == null);


        if (miasistente instanceof Administrador) {
            do {
                try {
                    System.out.println("MENÚ PARA ADMINISTRADORES DE DELECTARE: ");
                    System.out.println("1. Gestionar eventos: ");
                    System.out.println("2. Gestionar usuarios: ");
                    System.out.println("3. Salir");

                    opcionAdmin = sc.nextInt();

                    if (opcionAdmin == 1) {
                        System.out.println("Menú para gestionar los eventos.");
                    } else if (opcionAdmin == 2) {
                        System.out.println("Menú para gestionar los usuarios: ");
                    } else if (opcionAdmin == 3) {
                        System.out.println("Saliendo...");
                    }
                } catch (InputMismatchException ex) {
                    System.out.println("Introduce carácter válido");
                    sc.next();
                }

            } while (opcionAdmin != 3);

        } else {

            do {
                try {
                    System.out.println("""
                            \n-----------------
                            ### DELECTARE ###
                            -----------------

                            1. Seleccionar evento.
                            2. Información reservas.
                            3. Salir.

                            Por favor, seleccione una opción:
                            """);
                    opcionEvento = sc.nextInt();

                    if (opcionEvento == 3) {
                        gestor_eventos.mostrar_eventos(miasistente);
                    } else if (opcionEvento == 3) {
                        gestor_eventos.informacion_reservas(miasistente);
                    } else if (opcionEvento == 3) {
                        System.out.println("Saliendo...");
                    } else {
                        System.out.println("Introduzca una opción válida.");
                    }
                } catch (InputMismatchException ex) {
                    System.out.println("erro");
                    sc.next();
                }

            } while (opcionEvento != 3);
        }


        {
            System.out.println("Introduce un número.");
        }

    }
}
