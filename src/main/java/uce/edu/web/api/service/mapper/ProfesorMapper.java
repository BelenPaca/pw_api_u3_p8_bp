package uce.edu.web.api.service.mapper;

import uce.edu.web.api.repository.modelo.Profesor;
import uce.edu.web.api.service.to.ProfesorTo;

public class ProfesorMapper {

    public static ProfesorTo toTo(Profesor profesor) {
        ProfesorTo pTo = new ProfesorTo();
        pTo.setId(profesor.getId());
        pTo.setNombre(profesor.getNombre());
        pTo.setApellido(profesor.getApellido());
        pTo.setEspecialidad(profesor.getEspecialidad());
        pTo.setEmail(profesor.getEmail());
        pTo.setFechaContratacion(profesor.getFechaContratacion());
        pTo.setTitulo(profesor.getTitulo());
        return pTo;
    }

    public static Profesor toEntity(ProfesorTo profesorTo) {
        Profesor p = new Profesor();
        p.setId(profesorTo.getId());
        p.setNombre(profesorTo.getNombre());
        p.setApellido(profesorTo.getApellido());
        p.setEspecialidad(profesorTo.getEspecialidad());
        p.setEmail(profesorTo.getEmail());
        p.setFechaContratacion(profesorTo.getFechaContratacion());
        p.setTitulo(profesorTo.getTitulo());
        return p;
    }





}
