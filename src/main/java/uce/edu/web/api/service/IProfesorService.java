package uce.edu.web.api.service;

import java.util.List;

import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.repository.modelo.Profesor;
import uce.edu.web.api.service.to.ProfesorTo;

public interface IProfesorService {

    public Profesor buscarPorID (Integer id);
    public List<Profesor> buscarTodos(String titulo);
    public void actualizarPorId(ProfesorTo profesorTo);
    public void actualizarParcialPorId(ProfesorTo profesorTo);
    public void borrarPorId(Integer id);
    public void guardar(ProfesorTo profesorTo);

}
