package Practica3Ev;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ValidacionesTest {

    @Test
    public void testNombre() {
        String input = "Jáíró Qú@Zñç";
        input=input.toUpperCase();
        String resultado = Validaciones.validarNombre(input);
        assertTrue(input!=resultado);

    }

    @Test
    public void TestFechaEvento() {
        String input = "1-1-2025";
        String resultado = Validaciones.validarFechaEvento(input);
        assertEquals(input, resultado);
    }

    @Test
    public void TestCorreo() {
        String input = "jáiro-cox@cox23gmail.com";
        String resultado = Validaciones.validarCorreo(input);
        assertEquals(input, resultado);
    }

    @Test
    public void TestTelefono() {
        String input = "645039666";
        String resultado = Validaciones.validarTelefono(input);
        assertEquals(input, resultado);
    }

    @Test
    public void TestContrasena() {
        String input = "HolaBue02";
        String resultado = Validaciones.validarContrasena(input);
        assertEquals(input, resultado);
    }

    @Test
    public void TestDouble() {
        String input = "5200.50";
        String resultado = Validaciones.validarDouble(input);
        assertEquals(input, resultado);
    }

    @Test
    public void TestInt() {
        String input = "520";
        String resultado = Validaciones.validarInt(input);
        assertEquals(input, resultado);
    }

    @Test
    public void ValidarFecha() {
        String input = "1-1-2000";
        String resultado = Validaciones.validarFecha(input);
        assertEquals(input, resultado);
    }

    @Test
    public void validarHora(){
        String input = "23:30";
        String resultado = Validaciones.validarHora(input);
        assertEquals(input, resultado);
    }

}


