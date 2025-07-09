package uce.edu.web.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
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
import uce.edu.web.api.repository.modelo.Estudiante;
import uce.edu.web.api.repository.modelo.Hijo;
import uce.edu.web.api.service.IEstudianteService;
import uce.edu.web.api.service.IHijoService;
import uce.edu.web.api.service.to.EstudianteTo;

@Path("/estudiantes")
public class EstudianteController {

    @Inject
    private IEstudianteService estudianteService;

    @Inject
    private IHijoService hijoService;

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Consultar estudiante por ID", description = "Obtiene un estudiante específico por su ID.")

    public Response consultarPorId(@PathParam("id") Integer id, @Context UriInfo uriInfo) {
        EstudianteTo estu = this.estudianteService.buscarPorId(id, uriInfo);
        return Response.status(227).entity(estu).build();
    }

    // ?genero=F&provincia=Pichincha
    @GET
    @Path("")
    @Operation(summary = "Consultar todos los estudiantes", description = "Obtiene una lista de todos los estudiantes registrados en el sistema.")
    public Response consultarTodos(@QueryParam("genero") String genero,
            @QueryParam("provincia") String provincia) {
        System.out.println(provincia);
        return Response.status(Response.Status.OK).entity(this.estudianteService.buscarTodos(genero)).build();
    }

    @POST
    @Path("")
    @Operation(summary = "Guardar un estudiante", description = "Permite guardar un estudiante en el sistema.")
    public Response guardar(@RequestBody Estudiante estudiante) {
        this.estudianteService.guardar(estudiante);
        return Response.status(Response.Status.CREATED).entity(estudiante).build();

    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Actualizar estudiante por ID", description = "Actualiza completamente un estudiante existente por su ID.")
    public Response actualizarPorId(@RequestBody Estudiante estudiante, @PathParam("id") Integer id) {
        estudiante.setId(id);
        this.estudianteService.actualizarPorId(estudiante);
        return Response.status(Response.Status.OK).entity(estudiante).build();

    }

    // @PATCH
    // @Path("/{id}")
    // @Operation(summary = "Actualizar parcialmente estudiante por ID", description
    // = "Actualiza campos específicos de un estudiante existente por su ID.")
    // public Response actualizarParcialPorId(@RequestBody Estudiante estudiante,
    // @PathParam("id") Integer id) {
    // estudiante.setId(id);
    // Estudiante e = this.estudianteService.buscarPorId(id);
    // if (estudiante.getApellido() != null) {
    // e.setApellido(estudiante.getApellido());
    // }
    // if (estudiante.getNombre() != null) {
    // e.setNombre(estudiante.getNombre());
    // }
    // if (estudiante.getFechaNacimiento() != null) {
    // e.setFechaNacimiento(estudiante.getFechaNacimiento());
    // }
    // this.estudianteService.actualizarParcialPorId(e);
    // return Response.status(Response.Status.OK).entity(e).build();
    // }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Borrar estudiante por ID", description = "Elimina un estudiante del sistema por su ID.")
    public Response borrarPorId(@PathParam("id") Integer id) {
        this.estudianteService.borrarPorId(id);
        return Response.status(Response.Status.NO_CONTENT).build();

    }

    // http://localhost:8081/estudiantes/1/hijos GET
    @GET
    @Path("/{id}/hijos")
    public List<Hijo> obtenerHijosPorId(@PathParam("id") Integer id) {
        return this.hijoService.buscarPorEstudianteId(id);
    }

}
