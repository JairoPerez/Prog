package Practica3Ev.Excepciones;

public class EmailExistenteException extends Exception{
    public EmailExistenteException() {
        super("\nEse email ya existe. ");
    }
}
