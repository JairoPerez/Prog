package Practica3Ev;

import java.time.LocalDate;

public class Asistente extends Usuario{
    private LocalDate fecha_nacimiento;

    public Asistente(){

    }

    public Asistente(String nombre, String apellidos, String email, String password, String dni, String telefono, LocalDate fecha_nacimiento) {
        super(nombre, apellidos, email, password, dni, telefono);
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public void info_basica(String nombre, String apellidos, String email, String dni){
        System.out.println(nombre);
        System.out.println(apellidos);
        System.out.println(email);
        System.out.println(dni);
    }


}
