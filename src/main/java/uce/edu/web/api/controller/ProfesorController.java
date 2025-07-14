package uce.edu.web.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.repository.modelo.Hijo;
import uce.edu.web.api.repository.modelo.HijoProfesor;
import uce.edu.web.api.repository.modelo.Profesor;
import uce.edu.web.api.service.IHijoProfesorService;
import uce.edu.web.api.service.IHijoService;
import uce.edu.web.api.service.IProfesorService;
import uce.edu.web.api.service.mapper.ProfesorMapper;
import uce.edu.web.api.service.to.ProfesorTo;

@Path("/profesores")
public class ProfesorController {

    @Inject
    private IProfesorService profesorService;

    @Inject
    private IHijoProfesorService hijoProfesorService;

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Consultar profesor por ID", description = "Obtiene un profesor específico por su ID.")
    
    public Response consultarPorId(@PathParam("id") Integer id, @Context UriInfo uriInfo) {
        ProfesorTo profe = ProfesorMapper.toTo(this.profesorService.buscarPorID(id));
        profe.buildURI(uriInfo);
        return Response.status(227).entity(profe).build();


    }
    //?titulo=Ing&ciudad=Quito

    @GET
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Consultar todos los profesores", description = "Obtiene una lista de todos los profesores registrados en el sistema.")
    public Response consultarTodos(@QueryParam ("titulo")String titulo,
            @QueryParam ("ciudad") String ciudad) {
        System.out.println(ciudad);
        return Response.status(Response.Status.OK).entity(this.profesorService.buscarTodos(titulo)).build();
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Guardar un profesor", description = "Permite guardar un profesor en el sistema.")
    public Response guardar(@RequestBody ProfesorTo profesorTo) {
        this.profesorService.guardar(profesorTo);
        return Response.status(Response.Status.CREATED).entity(profesorTo).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Actualizar profesor por ID", description = "Actualiza un profesor específico por su ID.")
    public Response actualizarPorId (ProfesorTo profesorTo, @PathParam("id") Integer id){
        profesorTo.setId(id);
        this.profesorService.actualizarPorId(profesorTo);
        return Response.status(Response.Status.OK).entity(profesorTo).build();

    }

    @PATCH
    @Path("/{id}")
    @Operation(summary = "Actualizar parcialmente profesor por ID", description = "Actualiza campos específicos de un profesor existente por su ID.")
    public Response actualizarParcialPorId (@RequestBody Profesor profesor, @PathParam("id") Integer id){
        profesor.setId(id);
        ProfesorTo pTo = ProfesorMapper.toTo(this.profesorService.buscarPorID(id));
        if (profesor.getApellido() != null) {
            pTo.setApellido(profesor.getApellido());            
            
        }
        if(profesor.getEmail() != null) {
            pTo.setEmail(profesor.getEmail());
        }
        if (profesor.getEspecialidad() != null) {
            pTo.setEspecialidad(profesor.getEspecialidad());
        }
        if (profesor.getFechaContratacion() != null) {
            pTo.setFechaContratacion(profesor.getFechaContratacion());
        }
        if (profesor.getNombre() != null) {
            pTo.setNombre(profesor.getNombre());
        }   
        this.profesorService.actualizarParcialPorId((pTo));
        return Response.status(Response.Status.OK).build();
        
    }
    
    @DELETE
    @Path("/{id}")
    @Operation(summary = "Borrar profesor por ID", description = "Elimina un profesor del sistema por su ID.")
    public Response borrarPorId (@PathParam ("id") Integer id) {
        this.profesorService.borrarPorId(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Path("/{id}/hijos")

    public List<HijoProfesor> obtenerHijosPorId(@PathParam("id") Integer id) {
        return this.hijoProfesorService.buscarPorProfesorId(id);



    }

}
