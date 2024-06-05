package Practica3Ev.clases;
public class Administrador extends Usuario{
    private int permisos;

    public Administrador(String nombre, String apellidos, String email, String password, String dni, String telefono, int permisos) {
        super(nombre, apellidos, email, password, dni, telefono);
    }


    public int getPermisos() {
        return permisos;
    }

    public void setPermisos(int permisos) {
        this.permisos = permisos;
    }
}
