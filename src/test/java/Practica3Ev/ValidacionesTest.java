package Practica3Ev;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

public class ValidacionesTest {

    @Test
    public void testNombre() {
        String input = "José Ñáéíóúz";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        String resultado = Validaciones.validar_nombre();
        assertEquals(input.toUpperCase(), resultado);
    }

    @Test
    public void TestCorreo1() {
        String input = "admin@admin.com";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        String resultado = Validaciones.validar_correo();
        assertEquals(input, resultado);
    }

    @Test
    public void TestTelefono() {
        String input = "645039666";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        //redirige la entrada estándar del sistema para que cualquier entrada solicitada por el programa provenga de la cadena de arriba
        System.setIn(in);

        String resultado = Validaciones.validar_telefono();
        assertEquals(input, resultado);
    }
    @Test
    public void TestContrasena(){
        String input = "Hola123v";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        String resultado = Validaciones.validar_contrasena();
        assertEquals(input, resultado);

    }
}


