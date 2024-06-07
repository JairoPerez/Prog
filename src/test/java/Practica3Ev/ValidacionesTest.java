package Practica3Ev;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ValidacionesTest {

    @Test
    public void testNombre() {
        String input = "José ÑáéíóÓÚúz";
        String resultado = Validaciones.validar_nombre(input);
        assertEquals(input.toUpperCase(), resultado);
    }

    @Test
    public void TestCorreo1() {
        String input = "jáiro@-cox23@gmail.com";
        String resultado = Validaciones.validar_correo(input);
        assertEquals(input, resultado);
    }

    @Test
    public void TestTelefono() {
        String input = "645039666";
        String resultado = Validaciones.validar_telefono(input);
        assertEquals(input, resultado);
    }
    @Test
    public void TestContrasena(){
        String input = "HolaBue02";
        String resultado = Validaciones.validar_contrasena(input);
        assertEquals(input, resultado);

    }
}


