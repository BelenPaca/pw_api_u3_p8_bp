package uce.edu.web.api.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.repository.IProfesorRepo;
import uce.edu.web.api.repository.modelo.Profesor;
import uce.edu.web.api.service.mapper.ProfesorMapper;
import uce.edu.web.api.service.to.ProfesorTo;

@ApplicationScoped
public class ProfesorServiceImpl implements IProfesorService {

    @Inject
    private IProfesorRepo profesorRepo;

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)

    @Override
    public Profesor buscarPorID(Integer id) {
        return this.profesorRepo.seleccionarPorId(id);

    }
    @Override
    public List<Profesor> buscarTodos(String titulo) {
        return this.profesorRepo.seleccionarTodos(titulo);
    }

    @Override
    public void actualizarPorId(ProfesorTo profesorTo) {
        this.profesorRepo.actualizarPorId(ProfesorMapper.toEntity(profesorTo));
    }

    @Override
    public void actualizarParcialPorId(ProfesorTo profesorTo) {
        this.profesorRepo.actualizarParcialPorId(ProfesorMapper.toEntity(profesorTo));
    }

    @Override
    public void borrarPorId(Integer id) {
        this.profesorRepo.borrarPorId(id);

    }

    @Override
    public void guardar(ProfesorTo profesorTo) {
        this.profesorRepo.insertar(ProfesorMapper.toEntity(profesorTo));
    }



}
