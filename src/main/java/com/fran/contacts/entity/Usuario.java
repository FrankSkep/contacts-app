package com.fran.contacts.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "usuarios", uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    private String email;
    private String password;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contacto> contactos;

    public Usuario(Integer id, String nombre, String apelido, String email, String password) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apelido;
        this.email = email;
        this.password = password;
    }

    public Usuario(String nombre, String apelido, String email, String password) {
        this.nombre = nombre;
        this.apellido = apelido;
        this.email = email;
        this.password = password;
    }

    public Usuario() {
    }
}
