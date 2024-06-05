package Practica3Ev.Excepciones;

public class ReservaInexistenteException extends Exception {
    public ReservaInexistenteException() {
        super("No tiene ninguna reserva hecha, haga una reserva para ver su información.");
    }

}
