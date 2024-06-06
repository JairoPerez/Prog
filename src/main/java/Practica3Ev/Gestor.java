package Practica3Ev;

import Practica3Ev.Excepciones.EmailExistenteException;
import Practica3Ev.Excepciones.ReservaInexistenteException;
import Practica3Ev.Excepciones.UsuarioNoEncontradoException;
import Practica3Ev.clases.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Gestor {
    ArrayList<Evento> listado_eventos;
    Sala[] listado_salas;
    ArrayList<Usuario> listado_usuarios;
    ArrayList<Reserva> listado_reservas;

    public Gestor() {
        listado_eventos = new ArrayList<>();
        listado_salas = new Sala[5];
        listado_usuarios = new ArrayList<>();
        listado_reservas = new ArrayList<>();
        info_inicial();
    }

    public ArrayList<Evento> getListado_eventos() {
        return listado_eventos;
    }

    public void setListado_eventos(ArrayList<Evento> listado_eventos) {
        this.listado_eventos = listado_eventos;
    }

    public Sala[] getListado_salas() {
        return listado_salas;
    }

    public void setListado_salas(Sala[] listado_salas) {
        this.listado_salas = listado_salas;
    }

    public ArrayList<Usuario> getListado_usuarios() {
        return listado_usuarios;
    }

    public void setListado_usuarios(ArrayList<Usuario> listado_usuarios) {
        this.listado_usuarios = listado_usuarios;
    }

    public ArrayList<Reserva> getListado_reservas() {
        return listado_reservas;
    }

    public void setListado_reservas(ArrayList<Reserva> listado_reservas) {
        this.listado_reservas = listado_reservas;
    }

    public Usuario login() {

        String email;
        String password;

        System.out.println("Introduzca su correo electrónico: ");
        email = Validaciones.validar_correo();

        System.out.println("Introduzca su contraseña: ");
        password = Validaciones.validar_contrasena();

        try {
            for (Usuario a : listado_usuarios) {

                if (email.equals(a.getEmail()) && password.equals(a.getPassword())) {
                    return a;
                }
            }
            throw new UsuarioNoEncontradoException();
        } catch (UsuarioNoEncontradoException ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println("El correo o la contraseña son incorrectos.");
        return null;
    }

    public Usuario registro() {

        String nombre;
        String apellidos;
        String email;
        String dni;
        String telefono;
        String password;
        LocalDate fecha_nacimiento;
        boolean esAdmin = false;
        boolean salir = false;

        do {
            System.out.println("Introduzca su nombre: ");
            nombre = Validaciones.validar_nombre();

            System.out.println("Introduzca sus apellidos: ");
            apellidos = Validaciones.validar_nombre();

            System.out.println("Introduzca su correo electrónico: ");
            email = Validaciones.validar_correo();

            try {
                for (Usuario a : listado_usuarios) {
                    if (email.equals(a.getEmail())) {
                        salir = true;
                        throw new EmailExistenteException();
                    }
                }
            } catch (EmailExistenteException ex) {
                System.out.println(ex.getMessage());
            }

        } while (salir);

        System.out.println("Introduzca su contraseña: ");
        password = Validaciones.validar_contrasena();

        System.out.println("Introduzca su DNI: ");
        dni = Validaciones.validar_dni();

        System.out.println("Introduzca su número de telefono: ");
        telefono = Validaciones.validar_telefono();

        System.out.println("Introduzca su fecha de nacimiento: ");
        fecha_nacimiento = Validaciones.validar_fecha();

        Usuario nuevoUsuario = new Asistente(nombre, apellidos, email, password, dni, telefono, fecha_nacimiento);

        listado_usuarios.add(nuevoUsuario);

        try {
            FileWriter fw = new FileWriter("src/main/java/Practica3Ev/data/usuarios.dat", true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(String.valueOf(nuevoUsuario));
            bw.close();
            System.out.println("\nAñadido correctamente");
            System.out.println(nuevoUsuario);
        }catch (IOException ex){
            System.out.println("Error al añadir contenido al archivo");
        }


        return nuevoUsuario;
    }


    public void info_inicial() {

        int identificador = 0;

        for (int i = 0; i < 5; i++) {

            ArrayList<Butaca> misbutacas = new ArrayList<>();

            for (char fila = 'A'; fila < 'A' + (int) (Math.random() * 6) + 3; fila++) {
                for (int columna = 1; columna < 6; columna++) {
                    identificador++;
                    String pos = fila + "" + columna;
                    misbutacas.add(new Butaca(pos, identificador, false, true));
                }
            }
            listado_salas[i] = new Sala("Sala " + i, misbutacas.size(), misbutacas, 100.0);
        }

        listado_usuarios.add(new Asistente("Jairo", "Pérez", "jairo.perez@gmail.com", "Hola123425", "49626489x", "645039666", LocalDate.of(2005, 1, 23)));
        listado_usuarios.add(new Asistente("Pepe", "Pérez", "jairo.perez@gmail.com", "Hola123425", "49626489x", "645039666", LocalDate.of(2005, 1, 23)));
        listado_usuarios.add(new Asistente("Ramon", "Pérez", "jairo.perez@gmail.com", "Hola123425", "49626489x", "645039666", LocalDate.of(2005, 1, 23)));
        listado_usuarios.add(new Administrador("root", "root", "admin@admin.com", "root12345", "49626489x", "645039665", 1));

        try{
            ArrayList<Usuario> asistentes_principito = new ArrayList<>();
            asistentes_principito.add(listado_usuarios.get(0));
            asistentes_principito.add(listado_usuarios.get(1));

            ArrayList<Usuario> asistentes_final_lec = new ArrayList<>();
            asistentes_final_lec.add(listado_usuarios.get(0));
            asistentes_final_lec.add(listado_usuarios.get(1));

            ArrayList<Usuario> asistentes_cisneNegro = new ArrayList<>();
            asistentes_cisneNegro.add(listado_usuarios.get(0));
            asistentes_cisneNegro.add(listado_usuarios.get(1));

            ArrayList<Usuario> asistentes_romeo_julieta = new ArrayList<>();
            asistentes_romeo_julieta.add(listado_usuarios.get(0));
            asistentes_romeo_julieta.add(listado_usuarios.get(1));

            ArrayList<Usuario> asistentes_bodas_de_sangre = new ArrayList<>();
            asistentes_bodas_de_sangre.add(listado_usuarios.get(0));
            asistentes_bodas_de_sangre.add(listado_usuarios.get(2));

            listado_eventos.add(new Evento("El principito", "Arnau Robles", listado_salas[0], LocalDate.of(2023, 5, 12), LocalTime.of(18, 0), 70, "Obra de teatro", listado_salas[0].total_butacas(), asistentes_principito));
            listado_eventos.add(new Evento("Final de la LEC", "ElYoya", listado_salas[1], LocalDate.of(2024, 6, 25), LocalTime.of(17, 0), 50, "Torneo de sports", listado_salas[1].total_butacas(), asistentes_final_lec));
            listado_eventos.add(new Evento("El cisne negro", "Maricarmen", listado_salas[2], LocalDate.of(2024, 3, 13), LocalTime.of(19, 0), 30, "Película", listado_salas[2].total_butacas(), asistentes_cisneNegro));
            listado_eventos.add(new Evento("Romeo y Julieta", "Johnny Sins", listado_salas[3], LocalDate.of(2024, 7, 9), LocalTime.of(20, 0), 40, "Obra de teatro", listado_salas[3].total_butacas(), asistentes_romeo_julieta));
            listado_eventos.add(new Evento("Bodas de sangre", "Lorca", listado_salas[4], LocalDate.of(2024, 4, 4), LocalTime.of(18, 30), 50, "Obra de teatro", listado_salas[4].total_butacas(), asistentes_bodas_de_sangre));

        }catch (ArrayIndexOutOfBoundsException ex){
            System.out.println("No hay ningun usuario listado.");
        }

    }

    public void mostrar_eventos(Usuario asistente) {
        Scanner sc = new Scanner(System.in);
        String opcion;
        ArrayList<Evento> eventos_ahora = new ArrayList<>();
        boolean volver = false;
        boolean salir = false;

        for (Evento e : listado_eventos) {
            if (LocalDate.now().isBefore(e.getFecha())) {
                eventos_ahora.add(e);
            }
        }

        do {
            int num = 1;
            System.out.println("Estos son los eventos disponibles actualmente: \n");

            for (Evento e : eventos_ahora) {
                System.out.println((num++) + ". " + e.getNombre());
            }

            System.out.println("\nSelecciona un evento para ver la información del mismo: ");
            opcion = sc.nextLine();
            Pattern pattern = Pattern.compile("[0-9]+");
            Matcher matcher = pattern.matcher(opcion);
            if (opcion.length() <= eventos_ahora.size()) {
                if (matcher.find()) {
                    int num_evento = Integer.parseInt(opcion) - 1;

                    if (num_evento >= 0 && num_evento < eventos_ahora.size()) {
                        eventos_ahora.get(num_evento).mostrar_info_evento();

                        do {
                            System.out.println("\nPulse 1 para comprar la entrada al evento, pulse 2 para volver.");
                            opcion = sc.nextLine();

                            if (opcion.equals("1")) {
                                hacer_reserva(eventos_ahora.get(num_evento), asistente);
                                salir = true;
                                volver = true;
                            } else if (opcion.equals("2")) {
                                volver = true;
                            } else {
                                System.out.println("Opción no válida");
                            }
                        } while (!volver);
                    } else {
                        System.out.println("Procura seleccionar el número de cualquier evento.");
                    }
                } else {
                    System.out.println("Recuerda seleccionar un número de evento válido.");
                }
            } else {
                System.out.println("Ese número no corresponde con ningún evento en la lista, seleccione una opción válida: ");
            }

        } while (!salir);
    }

    public void mostrar_asistentes() {
        for (Usuario a : listado_usuarios) {
            System.out.println(a);
        }
    }

    public String hacer_reserva(Evento evento, Usuario asistente) {
        String token = Validaciones.crear_token();
        String asiento;
        Butaca butaca = null;
        boolean salir = false;

        do {
            System.out.println("\nSeleccione un asiento para el evento: \n");
            evento.mostrar_butacas();
            System.out.println();
            asiento = Validaciones.comprobar_asiento();

            for (Butaca b : evento.getSala().getLista_butacas()) {
                if (b.getPosicion().equals(asiento)) {
                    butaca = b;
                    break;
                }
            }
            if (butaca != null) {
                butaca.setDisponible(false);
                System.out.println("¡Esta butaca está libre!\n");
                salir = true;
            } else {
                System.out.println("Esta butaca está ocupada escoja otra, por favor: ");
            }

//            System.out.println("Resumen de la reserva: ");
//            System.out.println("Evento al que asistirá: " + evento.getNombre());
//            System.out.println("Sala del evento: " + evento.getSala().getNombre());
//            System.out.println("Fecha y hora del evento " + evento.getFecha() + " a las " + evento.getHora());
//            System.out.println("Precio del evento: " + evento.getPrecio());

            System.out.println("\n¿De que manera quiere efectuar el pago?");
            Validaciones.metodo_pago();

        } while (!salir);

        //listado_reservas.add(new Reserva(token, asistente, evento, butaca, evento.getFecha(), evento.getHora()));

        System.out.println("Aquí tienes el token de tu reserva procura no perderlo: " + token + "\n");

        return token;
    }

    public void informacion_reservas(Usuario asistente) {

        try {
            if (!listado_reservas.isEmpty()) {
                for (Reserva r : listado_reservas) {
                    if (r.getAsistente().equals(asistente)) {
                        r.mostrar_info_reserva();
                    } else {
                        throw new ReservaInexistenteException();
                    }
                }
            }
        } catch (ReservaInexistenteException ex) {
            System.out.println(ex.getMessage());
        }


    }
}
