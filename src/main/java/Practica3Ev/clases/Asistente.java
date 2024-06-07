package Practica3Ev.clases;

public class Asistente extends Usuario{
    private String fecha_nacimiento;

    public Asistente(){

    }

    public Asistente(String nombre, String apellidos, String email, String password, String dni, String telefono, String fecha_nacimiento) {
        super(nombre, apellidos, email, password, dni, telefono);
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public void info_basica(String nombre, String apellidos, String email, String dni, String fecha_nacimiento){
        System.out.println(nombre);
        System.out.println(apellidos);
        System.out.println(email);
        System.out.println(dni);
        System.out.println(fecha_nacimiento);
    }


}
