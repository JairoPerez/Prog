package Practica3Ev.Excepciones;

public class ContrasenaIncorrectaException extends Exception {
    public ContrasenaIncorrectaException() {
        super("Contraseña no válida.");
    }

}
