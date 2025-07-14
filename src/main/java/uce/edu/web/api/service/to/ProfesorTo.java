package uce.edu.web.api.service.to;

import java.net.URI;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.Column;
import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.controller.ProfesorController;

public class ProfesorTo {
    private Integer id;
    private String nombre;
    private String apellido;
    private String especialidad;
    private String email;
    private LocalDate fechaContratacion;
    private String titulo;

    public Map<String, String>_links = new HashMap<>();

    public void buildURI(UriInfo uriInfo) {
        URI todosHijos = uriInfo.getBaseUriBuilder()
                .path(ProfesorController.class).path(ProfesorController.class, "obtenerHijosPorId").build(id);

                _links.put("hijosP", todosHijos.toString());


    }

    public ProfesorTo() {
        
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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Map<String, String> get_links() {
        return _links;
    }

    public void set_links(Map<String, String> _links) {
        this._links = _links;
    }

    

}
