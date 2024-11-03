package com.fran.contacts.dto;

public class RegisterRequest {

    private Integer id;
    private String nombre;
    private String apellido;
    private String email;
    private String password;

    public RegisterRequest(Integer id, String nombre, String apelido, String email, String password) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apelido;
        this.email = email;
        this.password = password;
    }

    public RegisterRequest(String nombre, String apelido, String email, String password) {
        this.nombre = nombre;
        this.apellido = apelido;
        this.email = email;
        this.password = password;
    }
    
    public RegisterRequest() {}

    public RegisterRequest(String email) {
        this.email = email;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apelido) {
        this.apellido = apelido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
