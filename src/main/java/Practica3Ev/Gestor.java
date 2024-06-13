package Practica3Ev;

import Practica3Ev.Excepciones.EmailExistenteException;
import Practica3Ev.Excepciones.ReservaInexistenteException;
import Practica3Ev.Excepciones.UsuarioNoEncontradoException;
import Practica3Ev.clases.*;

import java.io.*;
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
        try {
            info_inicial();
        } catch (IOException ex) {
            System.out.println("Problemas en la conexión con el fichero");
        }

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
        Scanner sc = new Scanner(System.in);
        String email;
        String password;

        do {
            System.out.println("Introduzca su correo electrónico: ");
            email = sc.nextLine();
            email = Validaciones.validarCorreo(email);
        } while (email.isEmpty());


        do {
            System.out.println("Introduzca su contraseña: ");
            password = sc.nextLine();
            password = Validaciones.validarContrasena(password);
        } while (password.isEmpty());

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
        Scanner sc = new Scanner(System.in);
        String nombre;
        String apellidos;
        String email;
        String dni;
        String telefono;
        String password;
        String fecha_nacimiento;
        boolean salir = false;

        do {
            do {
                System.out.println("Introduzca su nombre: ");
                nombre = sc.nextLine();
                nombre = Validaciones.validarNombre(nombre);

            } while (nombre.isEmpty());

            do {
                System.out.println("Introduzca sus apellidos: ");
                apellidos = sc.nextLine();
                apellidos = Validaciones.validarNombre(apellidos);
            } while (apellidos.isEmpty());

            do {
                System.out.println("Introduzca su correo electrónico: ");
                email = sc.nextLine();
                email = Validaciones.validarCorreo(email);
            } while (email.isEmpty());

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

        do {
            System.out.println("Introduzca su contraseña: ");
            password = sc.nextLine();
            password = Validaciones.validarContrasena(password);
        } while (password.isEmpty());


        do {
            System.out.println("Introduzca su DNI: ");
            dni = sc.nextLine();
            dni = Validaciones.validarDni(dni);
        } while (dni.isEmpty());


        do {
            System.out.println("Introduzca su número de telefono: ");
            telefono = sc.nextLine();
            telefono = Validaciones.validarTelefono(telefono);
        } while (telefono.isEmpty());


        do {
            System.out.println("Introduzca su fecha de nacimiento: ");
            fecha_nacimiento = sc.nextLine();
            fecha_nacimiento = Validaciones.validarFecha(fecha_nacimiento);
        } while (fecha_nacimiento.isEmpty());


        Usuario nuevoUsuario = new Asistente(nombre, apellidos, email, password, dni, telefono, fecha_nacimiento);

        listado_usuarios.add(nuevoUsuario);

        try {
            volcarUsuarios();
        } catch (IOException ex) {
            System.out.println("Error de entrada/salida");
        }

        System.out.println("Usuario creado correctamente");
        return nuevoUsuario;
    }

    public void info_inicial() throws IOException {

        int identificador = 0;

        for (int i = 0; i < 5; i++) {

            ArrayList<Butaca> misbutacas = new ArrayList<>();

            for (char fila = 'A'; fila < 'A' + (int) (Math.random() * 6) + 3; fila++) {
                for (int columna = 1; columna < 6; columna++) {
                    identificador++;
                    String posicion = fila + "" + columna;
                    misbutacas.add(new Butaca(posicion, identificador, false, true));
                }
            }
            listado_salas[i] = new Sala("Sala " + i, misbutacas.size(), misbutacas, 100.0);
        }

        /*
        listado_usuarios.add(new Asistente("Jairo", "Pérez", "jairo.perez@gmail.com", "Hola1212", "49626489x", "645039666", "12-04-2000"));
        listado_usuarios.add(new Asistente("Pepe", "Pérez", "pepe@gmail.com", "Hola1223", "49626489x", "645039666", "12-04-2000"));
        listado_usuarios.add(new Asistente("Ramon", "Pérez", "johnny@gmail.com", "Hola1223", "49626489x", "645039666", "12-04-2000"));
        listado_usuarios.add(new Administrador("root", "root", "admin@admin.com", "root2005", "49626489x", "645039665", 1));
        volcarUsuarios();*/
        leerUsuario();

        /*
        listado_eventos.add(new Evento("El principito", "Mari Carmen Ortuño", listado_salas[0], LocalDate.of(2025, 5, 12), LocalTime.of(18, 0), 70, "Obra de teatro", listado_salas[0].total_butacas(), listado_usuarios));
        listado_eventos.add(new Evento("Final de la LEC", "Isabel Lafuente Garcia", listado_salas[1], LocalDate.of(2025, 6, 25), LocalTime.of(17, 0), 50, "Torneo de sports", listado_salas[1].total_butacas(), listado_usuarios));
        listado_eventos.add(new Evento("El cisne negro", "Victor Sarabia Simon", listado_salas[2], LocalDate.of(2025, 3, 13), LocalTime.of(19, 0), 30, "Película", listado_salas[2].total_butacas(), listado_usuarios));
        listado_eventos.add(new Evento("Romeo y Julieta", "Johnny Sins", listado_salas[3], LocalDate.of(2024, 7, 9), LocalTime.of(20, 0), 40, "Obra de teatro", listado_salas[3].total_butacas(), listado_usuarios));
        listado_eventos.add(new Evento("Bodas de sangre", "Lorca", listado_salas[4], LocalDate.of(2024, 10, 4), LocalTime.of(18, 30), 50, "Obra de teatro", listado_salas[4].total_butacas(), listado_usuarios));
        volcarEventos();*/
        leerEventos();

        /*
        listado_reservas.add(new Reserva("1", listado_usuarios.get(0),listado_eventos.get(0),listado_salas[1].getLista_butacas().get(0),LocalDate.of(2025,12,2), LocalTime.of(12,30)));
        listado_reservas.add(new Reserva("2", listado_usuarios.get(1),listado_eventos.get(1),listado_salas[2].getLista_butacas().get(1),LocalDate.of(2025,11,2), LocalTime.of(13,30)));
        listado_reservas.add(new Reserva("3", listado_usuarios.get(2),listado_eventos.get(2),listado_salas[3].getLista_butacas().get(2),LocalDate.of(2025,10,2), LocalTime.of(14,30)));
        listado_reservas.add(new Reserva("4", listado_usuarios.get(3),listado_eventos.get(3),listado_salas[0].getLista_butacas().get(3),LocalDate.of(2025,9,2), LocalTime.of(15,30)));
        volcarReservas();*/
        leerReservas();


        try {
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

        } catch (ArrayIndexOutOfBoundsException ex) {
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
        String token = Validaciones.crearToken();
        String asiento;
        Butaca butaca = null;
        boolean salir = false;
        Scanner sc = new Scanner(System.in);

        do {
            do {
                System.out.println("\nSeleccione un asiento para el evento: \n");
                evento.mostrar_butacas();
                System.out.println();
                asiento = sc.nextLine();
                asiento = Validaciones.comprobarAsiento(asiento);
            } while (asiento.isEmpty());

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
        } while (!salir);
        System.out.println("\n¿De que manera quiere efectuar el pago?");
        Validaciones.metodoPago();

        try {
            listado_reservas.add(new Reserva(token, asistente, evento, butaca, evento.getFecha(), evento.getHora()));
            volcarReservas();
        } catch (IOException ex) {
            System.out.println("Error de entrada/salida");
        }

        System.out.println("Aquí tienes el token de tu reserva procura no perderlo: " + token + "\n");

        return token;
    }

    public void informacion_reservas(Usuario asistente) {
        boolean reservaEncontrada = false;
        try {
            if (!listado_reservas.isEmpty()) {
                for (Reserva r : listado_reservas) {
                    if (r.getAsistente().equals(asistente)) {
                        r.mostrar_info_reserva();
                        reservaEncontrada = true;
                    }
                }
            }
            if (!reservaEncontrada) {
                throw new ReservaInexistenteException();
            }
        } catch (ReservaInexistenteException ex) {
            System.out.println(ex.getMessage());
        }


    }

    public void volcarUsuarios() throws IOException {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            fos = new FileOutputStream("src/main/java/Practica3Ev/data/usuarios.dat");
            oos = new ObjectOutputStream(fos);
            for (Usuario u : listado_usuarios) {
                oos.writeObject(u);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("No se ha encontrado el archivo.");
        } finally {
            oos.flush();
            oos.close();
            fos.close();
        }
    }

    public void volcarEventos() throws IOException {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream("src/main/java/Practica3Ev/data/eventos.dat");
            oos = new ObjectOutputStream(fos);
            for (Evento e : listado_eventos) {
                oos.writeObject(e);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("No se ha encontrado el archivo.");
        } finally {
            oos.flush();
            oos.close();
            fos.close();
        }
    }

    public void volcarReservas() throws IOException {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream("src/main/java/Practica3Ev/data/reservas.dat");
            oos = new ObjectOutputStream(fos);
            for (Reserva r : listado_reservas) {
                oos.writeObject(r);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("No se ha encontrado el archivo.");
        } finally {
            oos.flush();
            oos.close();
            fos.close();
        }
    }

    public void leerUsuario() throws IOException {
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try {
            fis = new FileInputStream("src/main/java/Practica3Ev/data/usuarios.dat");
            ois = new ObjectInputStream(fis);

            while (true) {
                Usuario u = (Usuario) ois.readObject();
                listado_usuarios.add(u);
            }
        } catch (EOFException ex) {
        } catch (ClassNotFoundException ex) {
            System.out.println("Hay un error en la clase");
        } finally {
            fis.close();
            ois.close();
        }
    }

    public void leerEventos() throws IOException {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream("src/main/java/Practica3Ev/data/eventos.dat");
            ois = new ObjectInputStream(fis);

            while (true) {
                Evento e = (Evento) ois.readObject();
                listado_eventos.add(e);
            }
        } catch (EOFException ex) {
        } catch (ClassNotFoundException ex) {
            System.out.println("Hay un error en la clase");
        } finally {
            fis.close();
            ois.close();
        }
    }

    public void leerReservas() throws IOException {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream("src/main/java/Practica3Ev/data/reservas.dat");
            ois = new ObjectInputStream(fis);

            while (true) {
                Reserva r = (Reserva) ois.readObject();
                listado_reservas.add(r);
            }
        } catch (EOFException ex) {
        } catch (ClassNotFoundException ex) {
            System.out.println("Hay un error en la clase");
        } finally {
            fis.close();
            ois.close();
        }

    }
}

