package uce.edu.web.api.controller;

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
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import uce.edu.web.api.repository.modelo.Profesor;
import uce.edu.web.api.service.IProfesorService;

@Path("/profesores")
public class ProfesorController {

    @Inject
    private IProfesorService profesorService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Consultar profesor por ID", description = "Obtiene un profesor específico por su ID.")
    public Response consultarPorId(@PathParam("id") Integer id) {
        return Response.status(227).entity(this.profesorService.buscarPorID(id)).build();


    }
    //?titulo=Ing&ciudad=Quito

    @GET
    @Path("")
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
    public Response guardar(@RequestBody Profesor profesor) {
        this.profesorService.guardar(profesor);
        return Response.status(Response.Status.CREATED).entity(profesor).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Actualizar profesor por ID", description = "Actualiza un profesor específico por su ID.")
    public Response actualizarPorId (Profesor profesor, @PathParam("id") Integer id){
        profesor.setId(id);
        this.profesorService.actualizarPorId(profesor);
        return Response.status(Response.Status.OK).entity(profesor).build();

    }

    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Actualizar parcialmente profesor por ID", description = "Actualiza campos específicos de un profesor existente por su ID.")
    public Response actualizarParcialPorId (@RequestBody Profesor profesor, @PathParam("id") Integer id){
        profesor.setId(id);
        Profesor profe = this.profesorService.buscarPorID(id);
        if (profesor.getApellido() != null) {
            profe.setApellido(profesor.getApellido());            
            
        }
        if(profesor.getEmail() != null) {
            profe.setEmail(profesor.getEmail());
        }
        if (profesor.getEspecialidad() != null) {
            profe.setEspecialidad(profesor.getEspecialidad());
        }
        if (profesor.getFechaContratacion() != null) {
            profe.setFechaContratacion(profesor.getFechaContratacion());
        }
        if (profesor.getNombre() != null) {
            profe.setNombre(profesor.getNombre());
        }   
        this.profesorService.actualizarParcialPorId(profe);
        return Response.status(Response.Status.OK).entity(profe).build();
        
    }
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Borrar profesor por ID", description = "Elimina un profesor del sistema por su ID.")
    public Response borrarPorId (@PathParam ("id") Integer id) {
        this.profesorService.borrarPorId(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

}
