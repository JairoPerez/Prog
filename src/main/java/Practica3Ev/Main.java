package Practica3Ev;

import Practica3Ev.clases.Administrador;
import Practica3Ev.clases.Usuario;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Gestor gestor_eventos = new Gestor();
        GestorAdministrador ges_admin = new GestorAdministrador(gestor_eventos.getListado_eventos(), gestor_eventos.getListado_salas());
        ges_admin.listarEventos();
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
                System.out.println("MENÚ PARA ADMINISTRADORES DE DELECTARE: ");
                System.out.println("1. Gestionar eventos: ");
                System.out.println("2. Salir");
                try {
                    opcionAdmin = sc.nextInt();

                    if (opcionAdmin == 1) {
                        System.out.println("Menú para gestionar los eventos.");
                        System.out.println("""
                                |-------------------------------------|
                                |# ADMINISTRADOR - GESTOR DE EVENTOS #|
                                |-------------------------------------|
                                |          1. Insertar evento         |
                                |          2. Eliminar evento         |
                                |          3. Listar eventos          |
                                |-------------------------------------|
                                   Seleccione una opción:
                                     """);

                        int opcion = sc.nextInt();

                        try {
                            if (opcion == 1) {
                                ges_admin.insertarEvento();
                                gestor_eventos.volcarEventos();
                            } else if (opcion == 2) {
                                ges_admin.eliminarEvento();
                                gestor_eventos.volcarEventos();
                            } else if (opcion == 3) {
                                ges_admin.listarEventos();
                            }
                        }catch (IOException ex){
                            System.out.println("ERROR ENTRADA/SALIDA");
                        }
                    } else if (opcionAdmin == 2) {
                        System.out.println("Saliendo...");
                    } else {
                        System.out.println("Introduzca una opción válida.");
                    }
                } catch (InputMismatchException ex) {
                    System.out.println("Introduce carácter númerico");
                }
            } while (opcionAdmin != 2);

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

                    if (opcionEvento == 1) {
                        gestor_eventos.mostrar_eventos(miasistente);
                    } else if (opcionEvento == 2) {
                        gestor_eventos.informacion_reservas(miasistente);
                    } else if (opcionEvento == 3) {
                        System.out.println("Saliendo...");
                    } else {
                        System.out.println("Introduzca una opción válida.");
                    }
                } catch (InputMismatchException ex) {
                    System.out.println("Introduce un valor númerico");

                }
            } while (opcionEvento != 3);
        }
    }
}
