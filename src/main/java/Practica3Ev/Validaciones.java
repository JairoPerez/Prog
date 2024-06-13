package Practica3Ev;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Jairo Pérez Ramón
 * @version 1.0
 * @since 2024-06-14
 */

public class Validaciones {


    public static String comprobarAsiento(String asiento) {
        boolean repetir = true;

        do {
            asiento = asiento.toUpperCase();

            //Si el asiento tiene más de 2 carácteres da como no válido
            if (asiento.length() != 2) {
                System.out.print("Asiento no válido, seleccione otro asiento por favor: ");
                return "";
            } else if (asiento.equals("A1") || asiento.equals("A2") || asiento.equals("A3")) {
                System.out.print("Asiento ocupado, seleccione otro asiento por favor: ");
                return "";
            } else if (asiento.charAt(0) != 'A' && asiento.charAt(0) != 'B' && asiento.charAt(0) != 'C') {
                System.out.print("Asiento no válido para esta función, seleccione otro asiento por favor: ");
                return "";
            } else if ((asiento.charAt(1) > '6' || asiento.charAt(1) < '1')) {
                System.out.print("Asiento no válido para esta función, seleccione otro asiento por favor: ");
                return "";
            } else {
                repetir = false;
            }
        } while (repetir);
        return asiento;
    }

    public static String validarNombre(String nombre) {
        char letra;
        boolean terminar = true;


        nombre = nombre.toUpperCase();

        if (!nombre.matches("^[a-zA-ZáéíóúñÑÁÉÍÓÚÄËÏÖÜ\\s]+$")) {
            System.out.println("Formato de entrada inválido. Por favor, introduce un valor correcto: ");
            return "";

        } else {
            terminar = false;
        }

        return nombre;
    }

    public static String validarCorreo(String correo) {
        String[] entre_arroba;
        String[] despues_punto;

        boolean necesario = false;
        boolean terminar = false;

        do {
            do {
                correo = correo.toLowerCase();
                correo = correo.replace(" ", "");


                entre_arroba = correo.split("@");

                if (!correo.contains("@") || !correo.contains(".")) {
                    System.out.println("Correo no válido, vuelva a introducirlo por favor: ");
                    return "";
                } else if (!entre_arroba[0].matches("[a-zA-ZáéíóúñÑÁÉÍÓÚÄËÏÖÜ0-9._-]+")) { //partimos el string por el @ para que sea más fácil validar con expresión regular ([usuario]@[dominio])
                    System.out.println("Correo no válido, vuelva a introducirlo por favor: ");
                    return "";
                } else {
                    necesario = true;
                }
            } while (!necesario);

            despues_punto = entre_arroba[1].split("\\.");
            //partimos el dominio por el punto para validar que no introduzca carácteres especiales no válidos ([dominio][tld])

            if (despues_punto.length < 2) {
                System.out.println("Formato de correo no válido, vuelva a introducirlo: ");
                return "";
            } else {
                if (!despues_punto[0].matches("[a-zA-ZáéíóúñÑÁÉÍÓÚÄËÏÖÜ0-9._-]+")) {
                    System.out.println("Correo no válido, vuelva a introducirlo por favor: ");
                    return "";
                } else if (!despues_punto[1].matches("[a-zA-ZáéíóúñÑÁÉÍÓÚÄËÏÖÜ]+")) { //puede contener número y/o carácteres especiales pero es muy raro
                    System.out.println("Correo no válido, vuelva a introducirlo por favor: ");
                    return "";
                } else {
                    terminar = true;
                }
            }


        } while (!terminar);

        return correo;
    }

    public static String validarContrasena(String contrasena) {
        boolean salir = false;


        do {
            Pattern letras = Pattern.compile("[a-zA-Z]+");
            Matcher matcher_letras = letras.matcher(contrasena);

            Pattern numeros = Pattern.compile("[0-9]+");
            Matcher matcher_numeros = numeros.matcher(contrasena);

            Pattern secuencia = Pattern.compile("123");
            Matcher matcher_secuencia = secuencia.matcher(contrasena);

            if (!matcher_secuencia.find() && (matcher_letras.find() && matcher_numeros.find()) == true) {
                if (contrasena.length() >= 8 && contrasena.length() <= 20) {
                    salir = true;
                } else {
                    System.out.println("Tu contraseña debe tener entre 8 a 20 carácteres");
                    return "";
                }

            } else {
                System.out.println("Contraseña no válida, la contraseña debe tener al menos un carácter y un número, no contener una secuencia de números y tener una longitud de 8 a 20 carácteres.");
                return "";
            }
        } while (!salir);
        return contrasena;
    }

    public static String validarTelefono(String numero_telefono) {
        char numero;
        boolean valido = true;

        do {
            numero_telefono = numero_telefono.replace("-", "");
            numero_telefono = numero_telefono.replace(" ", "");

            if (numero_telefono.length() != 9) {
                System.out.println("Número de teléfono no válido, vuelve a introducirlo: ");
                return "";
            } else {
                for (int i = 1; i < numero_telefono.length(); i++) {
                    numero = numero_telefono.charAt(i);

                    if (numero_telefono.charAt(0) < '6' || numero_telefono.charAt(0) > '7') {
                        System.out.println("Número de teléfono no válido, vuelva a introducirlo: ");
                        return "";
                    } else if (numero < '0' || numero > '9') {
                        System.out.println("Número de teléfono no válido, vuelva a introducirlo: ");
                        return "";

                    } else {
                        valido = false;
                    }
                }
            }
        } while (valido);
        return numero_telefono;
    }

    public static String validarDni(String dni) {
        String numeros_dni, letra_dni, letras_validas = "TRWAGMYFPDXBNJZSQVHLCKE";
        int valor;
        char total;
        boolean bucle = true;
        boolean dni_mal = true;


        do {

            do {
                dni = dni.toUpperCase();
                dni = dni.replace(" ", "");

                if (dni.length() != 9) {
                    System.out.println("DNI no válido, vuelve a introducirlo por favor: ");
                    return "";
                } else {
                    numeros_dni = dni.substring(0, 8);
                    letra_dni = dni.substring(8, 9);

                    for (int i = 0; i < numeros_dni.length(); i++) {
                        total = numeros_dni.charAt(i);

                        if ((total < '0' || total > '9')) {
                            System.out.println("DNI incorrecto, vuelve a introducirlo: ");
                            return "";
                        } else {
                            dni_mal = false;
                        }
                    }
                }
            } while (dni_mal);

            try {
                numeros_dni = dni.substring(0, 8);
                letra_dni = dni.substring(8, 9);

                valor = Integer.parseInt(numeros_dni);
                valor = valor % 23;
                if (!letra_dni.equals(String.valueOf(letras_validas.charAt(valor)))) {
                    System.out.println("DNI incorrecto, por favor vuelva a introducirlo: ");
                    return "";
                } else {
                    bucle = false;
                }
            } catch (NumberFormatException ex) {
                System.out.println("DNI incorrecto, por favor vuelva a introducirlo: ");
                return "";
            }

        } while (bucle);

        return dni;
    }

    public static String crearToken() {
        UUID token = UUID.randomUUID();
        return token.toString();
    }

    public static String validarFecha(String fecha) {
        String[] fecha_partes;
        LocalDate fecha_validada = null;
        LocalDate fecha_mayor;
        int dia;
        int mes;
        int anno;
        boolean salir = false;
        boolean volver = false;

        fecha = fecha.replace("/", "-");
        fecha = fecha.replace(" ", "-");

        Pattern pattern = Pattern.compile("^[0-9]{1,2}-[0-9]{1,2}-[0-9]{4}$");
        Matcher matcher = pattern.matcher(fecha);

        if (matcher.find()) {
            fecha_partes = fecha.split("-");

            dia = Integer.parseInt(fecha_partes[0]);
            mes = Integer.parseInt(fecha_partes[1]);
            anno = Integer.parseInt(fecha_partes[2]);

            if (mes > 12 || mes < 1) {
                System.out.println("Fecha inapropiada vuelva a introducirla, por favor: ");
                return "";
            } else if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) {
                if (dia < 1 || dia > 31) {
                    System.out.println("Fecha no válida, vuelva a introducirla, por favor: ");
                    return "";
                }

            } else if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
                if (dia < 1 || dia > 30) {
                    System.out.println("Fecha no válida, vuelva a introducirla, por favor: ");
                    return "";
                }

            } else {
                if ((anno % 4 == 0 || anno % 400 == 0) && anno % 100 != 0) {
                    if (dia < 1 || dia > 29) {
                        System.out.println("Fecha inapropiada vuelva a introducirla, por favor: ");
                        return "";
                    }
                } else {
                    if (dia < 1 || dia > 28) {
                        System.out.println("Fecha no válida, vuelva a introducirla, por favor: ");
                        return "";
                    }
                }
            }

            fecha_validada = LocalDate.of(anno, mes, dia);
            fecha_mayor = LocalDate.now();

            if (anno < 1935 || fecha_validada.isAfter(fecha_mayor.minusYears(18))) {
                System.out.println("Lo siento, necesita tener 18 años debido a razones de seguridad.");
                return "";
            } else {
                salir = true;
            }
        }
        try {
            fecha = fecha_validada.toString();
        } catch (NullPointerException ex) {
            System.out.println("Ha introducido una fecha incorrecta");
            return "";
        }
        return fecha;
    }

    public static String validarFechaEvento(String fecha) {
        String[] fecha_partes;
        LocalDate fecha_validada = null;
        LocalDate fecha_mayor;
        int dia;
        int mes;
        int anno;
        boolean salir = false;
        boolean volver = false;

        fecha = fecha.replace("/", "-");
        fecha = fecha.replace(" ", "-");

        Pattern pattern = Pattern.compile("^[0-9]{1,2}-[0-9]{1,2}-[0-9]{4}$");
        Matcher matcher = pattern.matcher(fecha);

        if (matcher.find()) {
            fecha_partes = fecha.split("-");

            dia = Integer.parseInt(fecha_partes[0]);
            mes = Integer.parseInt(fecha_partes[1]);
            anno = Integer.parseInt(fecha_partes[2]);

            if (mes > 12 || mes < 1) {
                System.out.println("Fecha inapropiada vuelva a introducirla, por favor: ");
                return "";
            } else if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) {
                if (dia < 1 || dia > 31) {
                    System.out.println("Fecha no válida, vuelva a introducirla, por favor: ");
                    return "";
                }

            } else if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
                if (dia < 1 || dia > 30) {
                    System.out.println("Fecha no válida, vuelva a introducirla, por favor: ");
                    return "";
                }

            } else {
                if ((anno % 4 == 0 || anno % 400 == 0) && anno % 100 != 0) {
                    if (dia < 1 || dia > 29) {
                        System.out.println("Fecha inapropiada vuelva a introducirla, por favor: ");
                        return "";
                    }
                } else {
                    if (dia < 1 || dia > 28) {
                        System.out.println("Fecha no válida, vuelva a introducirla, por favor: ");
                        return "";
                    }
                }
            }

            fecha_validada = LocalDate.of(anno, mes, dia);

            if (fecha_validada.isBefore(LocalDate.now())) {
                System.out.println("Lo siento, para crear un evento debe ser del dia de hoy hacia delante.");
                return "";
            } else {
                salir = true;
            }
        }
        try {
            fecha = fecha_validada.toString();
        } catch (NullPointerException ex) {
            System.out.println("Ha introducido una fecha incorrecta");
            return "";
        }
        return fecha;
    }

    public static String validarHora(String hora) {
        String[] horaPartes;
        LocalTime horaValidada = null;
        int horaBien = 0;
        int minuto = 0;
        int segundo;
        boolean salir = false;
        boolean volver = false;

        hora = hora.replace("/", ":");
        hora = hora.replace(" ", ":");

        Pattern pattern = Pattern.compile("^[0-9]{1,2}:[0-9]{1,2}$");
        Matcher matcher = pattern.matcher(hora);

        if (matcher.find()) {
            horaPartes = hora.split(":");

            horaBien = Integer.parseInt(horaPartes[0]);
            minuto = Integer.parseInt(horaPartes[1]);

            if (horaBien > 23 || horaBien < 0) {
                System.out.println("Hora inapropiada vuelva a introducirla, por favor: ");
                return "";
            }
            if (minuto > 59 || minuto < 0) {
                System.out.println("Minuto no válido, vuelva a introducirla, por favor: ");
                return "";
            }
            horaValidada = LocalTime.of(horaBien, minuto);
        }
        try {
            boolean b = hora == horaValidada.toString();
        } catch (NullPointerException ex) {
            System.out.println("Introduce una hora adecuada");
            return "";
        }

        return hora;
    }

    public static String metodoPago() {
        Scanner sc = new Scanner(System.in);
        String opcion, pago, iban, iban_convertido, letras_iban, numeros_iban, cuatro_digitos, numeros_iban_pasado;
        char letras, numeros;
        boolean valido = true;
        boolean iban_mal = true;
        BigInteger resultado;

        do {
            System.out.println("""
                    1. Transferencia bancaria
                    2. Paypal
                    3. Bizum
                    """);
            opcion = sc.nextLine();

            if (!(opcion.equals("1")) && !(opcion.equals("2")) && !(opcion.equals("3"))) {
                System.out.println("Opción no válida, vuelva a escoger la opción que desea: ");
            }

        } while (!(opcion.equals("1")) && !(opcion.equals("2")) && !(opcion.equals("3")));

        switch (opcion) {
            case "1":
                System.out.println("\nIntroduzca su numero de cuenta bancaria: ");
                do {
                    try {
                        do {
                            iban = sc.nextLine();
                            iban = iban.toUpperCase();
                            iban = iban.replace(" ", "");
                            if (iban.length() != 24) {
                                System.out.println("Numero de cuenta no válido, vuelva a introducirlo por favor: ");
                            } else {
                                letras_iban = iban.substring(0, 2);
                                numeros_iban = iban.substring(2);

                                for (int i = 0; i < letras_iban.length(); i++) {
                                    letras = letras_iban.charAt(i);
                                    if (letras != 'E' && letras != 'S') {
                                        System.out.println("Cuenta bancaria no encontrada, vuelva a introducirla: ");
                                        iban_mal = true;
                                        break;
                                    }
                                }

                                for (int j = 0; j < numeros_iban.length(); j++) {
                                    numeros = numeros_iban.charAt(j);
                                    if (numeros < '0' || numeros > '9') {
                                        System.out.println("Cuenta bancaria no encontrada, vuelva a introducirla: ");
                                        iban_mal = true;
                                        break;
                                    } else {
                                        iban_mal = false;
                                    }
                                }
                            }
                        } while (iban_mal);

                        cuatro_digitos = iban.substring(0, 4);
                        numeros_iban_pasado = iban.substring(4);

                        iban_convertido = numeros_iban_pasado + cuatro_digitos;
                        iban_convertido = iban_convertido.replace("E", "14");
                        iban_convertido = iban_convertido.replace("S", "28");


                        BigInteger iban_numero = new BigInteger(iban_convertido);
                        resultado = iban_numero.mod(BigInteger.valueOf((97)));


                        //Primero validamos que los dos primeros carateres sean letras y lo demás números

                        /*Después reemplazamos los 4 primeros dígitos del IBAN al final y convertimos las letras a su valor numérico,
                         *hacemos una división del IBAN convertido a números entre 97 y si el resto da 1 el IBAN es válido */

                        if (!String.valueOf(resultado).equals("1")) {
                            System.out.println("Cuenta bancaria incorrecta, vuelva a introducirla por favor: ");
                        } else {
                            System.out.println("""
                                                                        
                                    Datos bancarios:
                                    Banco: BBVA
                                    Número de cuenta:\s""" + iban + """
                                                                        
                                    Total a pagar: Un 5 , muchas gracias :D .
                                    """);
                            valido = false;
                        }
                    } catch (NumberFormatException ex) {
                        System.out.println("Numero de cuenta no válido, vuelva a introducirlo por favor:");
                    }
                } while (valido);
                break;

            //Si el usuario escoge de opción de pago Paypal tendrá que introducir su cuenta, y si elige bizum tiene que enviar dinero al número indicado

            case "2":
                System.out.println("\nIntroduce una cuenta de Paypal: ");
                String email = sc.nextLine();
                validarCorreo(email);

                System.out.println("Introduzca su contraseña: ");
                sc.nextLine();
                System.out.println("\nPaypal registrado, se le cobrará en instantes\n");
                break;
            case "3":
                System.out.println("\nHaz un bizum al: 645039666\n");
                break;
        }

        if (opcion.equals("1")) {
            pago = "El método de pago escogido ha sido: Transferencia bancaria";
        } else if (opcion.equals("2")) {
            pago = "El método de pago escogido ha sido: Paypal";
        } else {
            pago = "El método de pago ha sido: Bizum";
        }
        return pago;
    }

    public static String validarInt(String int_valido) {
        try {
            Integer.parseInt(int_valido);
        } catch (NumberFormatException ex) {
            System.out.println("Ha introducido un carácter no númerico o con decimales");
            return "";
        }

        return int_valido;
    }

    public static String validarDouble(String double_valido) {
        try {
            Double.parseDouble(double_valido);
        } catch (NumberFormatException ex) {
            System.out.println("Introduce un precio válido");
            return "";
        }
        return double_valido;
    }
}
