/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package streaming;

/**
 *
 * @author djurado
 */
public class Usuario {

    public enum Rol {
        PROFESOR,
        AYUDANTE,
        ESTUDIANTE
    }

    private String nombre;
    private Rol rol;

    public Usuario(String nombre, Rol rol) {
        this.nombre = nombre;
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }

    public Rol getRol() {
        return rol;
    }

    public boolean esEstudiante() {
        return rol == Rol.ESTUDIANTE;
    }

    public boolean esProfesor() {
        return rol == Rol.PROFESOR;
    }

    public boolean esAyudante() {
        return rol == Rol.AYUDANTE;
    }
}
