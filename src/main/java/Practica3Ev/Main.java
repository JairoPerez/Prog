package Practica3Ev;
import Practica3Ev.clases.Administrador;
import Practica3Ev.clases.Usuario;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Jairo Pérez Ramón
 * @version 1.0
 * @since 2024-02-10
 */

public class Main {
    public static void main(String[] args) {

        Gestor gestor_eventos = new Gestor();
        GestorAdministrador ges_admin = new GestorAdministrador(gestor_eventos.getListado_eventos(), gestor_eventos.getListado_salas());
        Usuario miAsistente = null;
        Scanner sc = new Scanner(System.in);
        int opcionAdmin = 0;
        int opcionEvento = 0;
        int opcionMenu;
        int opcion = 0;
        
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

                opcionMenu = sc.nextInt();

                switch (opcionMenu) {
                    case 1:
                        miAsistente = gestor_eventos.login();
                        break;
                    case 2:
                        miAsistente = gestor_eventos.registro();
                        break;
                    case 3:
                        System.out.println("Saliendo...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Introduzca una opción válida.");
                }

            } catch (InputMismatchException ex) {
                System.out.println("Introduce un valor correspondiente");
                sc.next();
            }
        } while (miAsistente == null);


        if (miAsistente instanceof Administrador) {

            do {
                System.out.println("MENÚ PARA ADMINISTRADORES DE DELECTARE: ");
                System.out.println("1. Gestionar eventos: ");
                System.out.println("2. Salir");

                try {
                    opcionAdmin = sc.nextInt();
                    if (opcionAdmin == 1) {
                        do {
                            System.out.println("Menú para gestionar los eventos.");
                            System.out.println("""
                                    |-------------------------------------|
                                    |# ADMINISTRADOR - GESTOR DE EVENTOS #|
                                    |-------------------------------------|
                                    |          1. Insertar evento         |
                                    |          2. Eliminar evento         |
                                    |          3. Listar eventos          |
                                    |          4. Mostrar información     |
                                    |          5. Salir                   |
                                    |-------------------------------------|
                                       Seleccione una opción:
                                         """);

                            try {
                                opcion = sc.nextInt();
                                if (opcion == 1) {
                                    ges_admin.insertarEvento();
                                    gestor_eventos.volcarEventos();
                                } else if (opcion == 2) {
                                    ges_admin.eliminarEvento();
                                    gestor_eventos.volcarEventos();
                                } else if (opcion == 3) {
                                    ges_admin.listarEventos();
                                } else if (opcion == 4) {
                                    ges_admin.listarEvento();
                                }
                            } catch (InputMismatchException ex) {
                                System.out.println("Introduce un carácter numérico");
                                sc.next();
                            } catch (IOException ex) {
                                System.out.println("ERROR DE ENTRADA/SALIDA");
                            }
                        } while (opcion != 5);


                    } else if (opcionAdmin == 2) {
                        System.out.println("Saliendo...");
                        System.exit(0);
                        break;
                    } else {
                        System.out.println("Introduzca una opción válida.");
                    }
                } catch (InputMismatchException ex) {
                    System.out.println("Introduce carácter númerico");
                    sc.next();
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
                        gestor_eventos.mostrar_eventos(miAsistente);
                    } else if (opcionEvento == 2) {
                        gestor_eventos.informacion_reservas(miAsistente);
                    } else if (opcionEvento == 3) {
                        System.out.println("Saliendo...");
                    } else {
                        System.out.println("Introduzca una opción válida.");
                    }
                } catch (InputMismatchException ex) {
                    System.out.println("Introduce un valor númerico");
                    sc.next();  //TODOS LOS INPUTMISMATCHEXCEPTION NECESITAN LLEVAR EL SC.NEXT PARA LIMPIAR LA LETRA DEL SCANNER

                }
            } while (opcionEvento != 3);
        }
    }
}
