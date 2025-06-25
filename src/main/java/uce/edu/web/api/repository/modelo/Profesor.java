package uce.edu.web.api.repository.modelo;

import java.time.LocalDate;

import io.quarkus.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "profesor")

public class Profesor {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "profe_id")
    private Integer id;

    @Column (name = "profe_nombre")
    private String nombre;

    @Column (name = "profe_apellido")
    private String apellido;

    @Column (name = "profe_especialidad")
    private String especialidad;

    @Column (name = "profe_email")
    private String email;

    @Column (name = "profe_fecha_contratacion")
    private LocalDate fechaContratacion;

    //SET Y GET

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

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(LocalDate fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    



}
