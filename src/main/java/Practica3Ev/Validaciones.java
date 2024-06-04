package Practica3Ev;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validaciones {
    public static String comprobar_asiento() {
        Scanner sc = new Scanner(System.in);
        String asiento;
        boolean repetir = true;

        do {
            asiento = sc.nextLine();
            asiento = asiento.toUpperCase();

            //Si el asiento tiene más de 2 carácteres da como no válido
            if (asiento.length() != 2) {
                System.out.print("Asiento no válido, seleccione otro asiento por favor: ");
            } else if (asiento.equals("A1") || asiento.equals("A2") || asiento.equals("A3")) {
                System.out.print("Asiento ocupado, seleccione otro asiento por favor: ");
            } else if (asiento.charAt(0) != 'A' && asiento.charAt(0) != 'B' && asiento.charAt(0) != 'C') {
                System.out.print("Asiento no válido para esta función, seleccione otro asiento por favor: ");
            } else if ((asiento.charAt(1) > '6' || asiento.charAt(1) < '1')) {
                System.out.print("Asiento no válido para esta función, seleccione otro asiento por favor: ");
            } else {
                repetir = false;
            }
        } while (repetir);
        return asiento;
    }

    //todo: Cambiar validacion nombre usando expresiones regulares
    public static String validar_nombre() {
        Scanner sc = new Scanner(System.in);
        String nombre_apellidos;
        char letra;
        boolean terminar = true;

        do {
            nombre_apellidos = sc.nextLine();
            nombre_apellidos = nombre_apellidos.toUpperCase();

            for (int i = 0; i < nombre_apellidos.length(); i++) {
                letra = nombre_apellidos.charAt(i);
                if ((letra < 'A' || letra > 'Z') && (letra != 'Ç' && letra != 'Ñ' && letra != 'Á' && letra != 'É' && letra != 'Í' && letra != 'Ó' && letra != 'Ú' && letra != ' ' && letra != 'Ä' && letra != 'Ë' && letra != 'Ï' && letra != 'Ö' && letra != 'Ü')) {
                    System.out.println("Error: Formato de entrada inválido. Por favor, introduce un valor válido: ");
                    terminar = true;
                    break;
                } else {
                    terminar = false;
                }
            }
        } while (terminar);
        return nombre_apellidos;
    }

    public static String validar_correo() {
        Scanner sc = new Scanner(System.in);
        String correo;
        String[] entre_arroba;
        String[] despues_punto;

        boolean necesario = false;
        boolean terminar = false;

        do {
            do {
                correo = sc.nextLine();
                correo = correo.toLowerCase();
                correo = correo.replace(" ", "");


                entre_arroba = correo.split("@");

                if (!correo.contains("@") || !correo.contains(".")) {
                    System.out.println("Correo no válido, vuelva a intorducirlo por favor: ");
                } else if (!entre_arroba[0].matches("[a-zA-Z0-9._-]+")) { //partimos el string por el @ para que sea más fácil validar con expresión regular ([usuario]@[dominio])
                    System.out.println("Correo no válido, vuelva a introducirlo por favor: ");
                } else {
                    necesario = true;
                }
            } while (!necesario);

            despues_punto = entre_arroba[1].split("\\.");
            //partimos el dominio por el punto para validar que no introduzca carácteres especiales no válidos ([dominio][tld])

            if(despues_punto.length < 2){
                System.out.println("Formato de correo no válido, vuelva a introducirlo: ");
            }else {
                if (!despues_punto[0].matches("[a-zA-Z0-9._-]+")) {
                    System.out.println("Correo no válido, vuelva a introducirlo por favor: ");
                } else if (!despues_punto[1].matches("[a-zA-Z]+")) { //puede contener número y/o carácteres especiales pero es muy raro
                    System.out.println("Correo no válido, vuelva a introducirlo por favor: "); //todo: como válidar si el usuario pone .com.co
                } else {
                    terminar = true;
                }
            }



        } while (!terminar);

        return correo;
    }

    public static String validar_contrasena() {
        Scanner sc = new Scanner(System.in);
        String contrasena;
        boolean salir = false;

        do {
            contrasena = sc.nextLine();

            Pattern letras = Pattern.compile("[a-zA-Z]+");
            Matcher matcher_letras = letras.matcher(contrasena);

            Pattern numeros = Pattern.compile("[0-9]+");
            Matcher matcher_numeros = letras.matcher(contrasena);

            Pattern secuencia = Pattern.compile("123|1234|12345");
            Matcher matcher_secuencia = letras.matcher(contrasena);

            Pattern especiales = Pattern.compile("[:-@] | [!-/] | [\\[-`]+");

            if (matcher_letras.find() && matcher_secuencia.find() && matcher_numeros.find()) {
                salir = true;
            } else {
                System.out.println("Contraseña no válida, la contraseña debe tener al menos un carácter y un número, no contener una secuencia de números y tener una longitud de 8 a 20 carácteres.");
            }

//            if (!contrasena.matches("[a-zA-Z0-9]"))  {
//                System.out.println("La contraseña debe contener al menos una letra y un número.");
//            } else if (contrasena.matches("123|1234|12345")) {
//                System.out.println("Procura no usar cadenas de números ya que pueden ser vulnerables.");
//            } else if (contrasena.length() < 8 || contrasena.length() > 20) {
//                System.out.println("La contraseña debe tener entre 8 y 20 carácteres");
//            } else {
//                salir = true;
//            }
        } while (!salir);


        return contrasena;
    }

    public static String validar_telefono() {
        Scanner sc = new Scanner(System.in);
        String numero_telefono;
        char numero;
        boolean valido = true;

        do {
            numero_telefono = sc.nextLine();
            numero_telefono = numero_telefono.replace("-", "");
            numero_telefono = numero_telefono.replace(" ", "");

            if (numero_telefono.length() != 9) {
                System.out.println("Número de teléfono no válido, vuelve a introducirlo: ");
            } else {
                for (int i = 1; i < numero_telefono.length(); i++) {
                    numero = numero_telefono.charAt(i);

                    if (numero_telefono.charAt(0) < '6' || numero_telefono.charAt(0) > '7') {
                        System.out.println("Número de teléfono no válido, vuelva a introducirlo: ");
                        if (numero < '0' || numero > '9') {
                            System.out.println("Número de teléfono no válido, vuelva a introducirlo: ");
                        }
                        valido = true;
                        break;

                    } else if (numero < '0' || numero > '9') {
                        System.out.println("Número de teléfono no válido, vuelva a introducirlo: ");
                        valido = true;
                        break;

                    } else {
                        valido = false;
                    }
                }
            }
        } while (valido);
        return numero_telefono;
    }

    public static String validar_dni() {
        Scanner sc = new Scanner(System.in);
        String dni, numeros_dni, letra_dni, letras_validas = "TRWAGMYFPDXBNJZSQVHLCKE";
        int valor;
        char total;
        boolean bucle = true;
        boolean dni_mal = true;


        do {
            do {
                dni = sc.nextLine();
                dni = dni.toUpperCase();
                dni = dni.replace(" ", "");

                if (dni.length() != 9) {
                    System.out.println("DNI no válido, vuelve a introducirlo por favor: ");
                } else {
                    numeros_dni = dni.substring(0, 8);
                    letra_dni = dni.substring(8, 9);

                    for (int i = 0; i < numeros_dni.length(); i++) {
                        total = numeros_dni.charAt(i);

                        if ((total < '0' || total > '9')) {
                            System.out.println("DNI incorrecto, vuelve a introducirlo: ");
                            dni_mal = true;
                            break;
                        } else {
                            dni_mal = false;
                        }
                    }
                }
            } while (dni_mal);

            numeros_dni = dni.substring(0, 8);
            letra_dni = dni.substring(8, 9);

            valor = Integer.parseInt(numeros_dni);
            valor = valor % 23;
            if (!letra_dni.equals(String.valueOf(letras_validas.charAt(valor)))) {
                System.out.println("DNI incorrecto, por favor vuelva a introducirlo: ");
            } else {
                bucle = false;
            }
        } while (bucle);

        return dni;
    }

    public static String crear_token() {
        String token;
        String token_final = "";
        String especiales = "!@#$%^&*";
        String numeros = "0123456789";
        String letras = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String caracteres_validos = letras + numeros + especiales; //Aquí creo una cadena de caracteres válidos concatenando los strings de los caracteres permitidos
        int numero;
        int contador_letras = 0, contador_nums = 0, contador_especiales = 0;
        double random;

        do { //Con este bucle for me aseguro de que la longitud del token sea de 17 carácteres
            for (int i = 0; i <= 16; i++) {
                random = Math.random() * caracteres_validos.length();
                numero = (int) random;
                token = String.valueOf(caracteres_validos.charAt(numero));

                /*Aquí he generado un número aleatorio sobre la longitud de los carácteres validos para crear el token
                 * Con este bucle for me encargo de que el máximo de carácteres sea 7 7 y 3 con contadores que iteran
                 * cuando el caracter generado es de ese tipo
                 * */
                for (int contador = 0; contador < token_final.length() + 1; ) {
                    if ((letras.contains(token))) {
                        contador_letras = contador_letras + 1;

                        //Cuando un contador se llena se reemplaza la cadena de los caracteres validos por la concatenación de los otros dos strings
                        //(en este caso las letras)

                        if (contador_letras == 7 && contador_nums != 7 && contador_especiales != 3) {
                            caracteres_validos = numeros + especiales;
                        } else if (contador_letras == 7 && contador_nums == 7 && contador_especiales != 3) {
                            caracteres_validos = especiales;
                        } else if (contador_letras == 7 && contador_nums != 7) {
                            caracteres_validos = numeros;
                        }
                    } else if ((numeros.contains(token))) {
                        contador_nums = contador_nums + 1;
                        if (contador_nums == 7 && contador_especiales != 3 && contador_letras != 7) {
                            caracteres_validos = letras + especiales;
                        } else if (contador_nums == 7 && contador_letras == 7 && contador_especiales != 3) {
                            caracteres_validos = especiales;
                        } else if (contador_nums == 7 && contador_letras != 7) {
                            caracteres_validos = letras;
                        }
                    } else if (especiales.contains(token)) {
                        contador_especiales = contador_especiales + 1;
                        if (contador_especiales == 3 && contador_letras != 7 && contador_nums != 7) {
                            caracteres_validos = letras + numeros;
                        } else if (contador_especiales == 3 && contador_nums == 7 && contador_letras != 7) {
                            caracteres_validos = letras;
                        } else if (contador_especiales == 3 && contador_nums != 7) {
                            caracteres_validos = numeros;
                        }
                    }

                    //Con este if hago que si algún caracter se repite se borre del token generado y le reste una iteración al contador pertinente
                    if (token_final.indexOf(token.charAt(contador)) != -1) {
                        if (letras.indexOf(token.charAt(contador)) != -1) {
                            contador_letras = contador_letras - 1;
                        } else if (numeros.indexOf(token.charAt(contador)) != -1) {
                            contador_nums = contador_nums - 1;
                        } else if (especiales.indexOf(token.charAt(contador)) != -1) {
                            contador_especiales = contador_especiales - 1;
                        }
                        token = token.replace(token, "");
                        i = i - 1;
                        break;
                    } else {
                        token_final = token_final.concat(token);
                    }
                    break;
                }
            }
        } while ((contador_nums != 7 && contador_letras != 7 && contador_especiales != 3) && token_final.length() != 17);
        //Salgo del bucle cuando los contadores estén llenos y la longitud del token sea 17

        return token_final;
    }

    public static LocalDate validar_fecha() {
        Scanner sc = new Scanner(System.in);

        String fecha;
        String[] fecha_partes;
        LocalDate fecha_validada = null;
        LocalDate fecha_mayor;
        int dia;
        int mes;
        int anno;
        boolean salir = false;
        boolean volver = false;

        do {
            do {
                fecha = sc.nextLine();
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
                        break;
                    } else if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) {
                        if (dia < 1 || dia > 31) {
                            System.out.println("Fecha no válida, vuelva a introducirla, por favor: ");
                            break;
                        }

                    } else if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
                        if (dia < 1 || dia > 30) {
                            System.out.println("Fecha no válida, vuelva a introducirla, por favor: ");
                            break;
                        }

                    } else {
                        if ((anno % 4 == 0 || anno % 400 == 0) && anno % 100 != 0) {
                            if (dia < 1 || dia > 29) {
                                System.out.println("Fecha inapropiada vuelva a introducirla, por favor: ");
                                break;
                            }
                        } else {
                            if (dia < 1 || dia > 28) {
                                System.out.println("Fecha no válida, vuelva a introducirla, por favor: ");
                                break;
                            }
                        }
                    }

                    fecha_validada = LocalDate.of(anno, mes, dia);
                    fecha_mayor = LocalDate.now();

                    if (anno < 1935 || fecha_validada.isAfter(fecha_mayor.minusYears(18))) {
                        System.out.println("Lo siento, para hacer una reserva debes tener al menos 18 años debido a razones de seguridad.");
                    } else {
                        salir = true;
                        volver = true;
                    }
                }
            } while (!volver);
        } while (!salir);
        return fecha_validada;
    }

    public static String metodo_pago() {
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
                                Banco: Quiero aprobar el curso pero no tengo tiempo ni para existir
                                Número de cuenta:\s""" + iban + """
                                                                    
                                Total a pagar: Todo cuanto se pueda y un poco más, muchas gracias.
                                """);
                        valido = false;
                    }
                } while (valido);

                break;

            //Si el usuario escoge de opción de pago Paypal tendrá que introducir su cuenta, y si elige bizum tiene que enviar dinero al número indicado

            case "2":
                System.out.println("\nIntroduce una cuenta de Paypal: ");
                validar_correo();
                System.out.println("Introduzca su contraseña: ");
                sc.nextLine();
                System.out.println("\nPaypal registrado, se le cobrará en instantes\n");
                break;
            case "3":
                System.out.println("\nHaz un bizum al: 633615163\n");
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
}
