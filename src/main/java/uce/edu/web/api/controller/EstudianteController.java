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
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.repository.modelo.Estudiante;
import uce.edu.web.api.repository.modelo.Hijo;
import uce.edu.web.api.service.IEstudianteService;
import uce.edu.web.api.service.IHijoService;
import uce.edu.web.api.service.mapper.EstudianteMapper;
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
        EstudianteTo estu = EstudianteMapper.toTo(this.estudianteService.buscarPorId(id));
        estu.buildURI(uriInfo);
        return Response.status(227).entity(estu).build();
    }

    // ?genero=F&provincia=Pichincha
    @GET
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Consultar todos los estudiantes", description = "Obtiene una lista de todos los estudiantes registrados en el sistema.")
    public Response consultarTodos(@QueryParam("genero") String genero,
            @QueryParam("provincia") String provincia) {
        System.out.println(provincia);
        return Response.status(Response.Status.OK).entity(this.estudianteService.buscarTodos(genero)).build();
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Guardar un estudiante", description = "Permite guardar un estudiante en el sistema.")
    public Response guardar(@RequestBody EstudianteTo estudianteTo) {
        this.estudianteService.guardar(estudianteTo);
        return Response.status(Response.Status.CREATED).entity(estudianteTo).build();

    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Actualizar estudiante por ID", description = "Actualiza completamente un estudiante existente por su ID.")
    public Response actualizarPorId(@RequestBody EstudianteTo estudianteTo, @PathParam("id") Integer id) {
        estudianteTo.setId(id);
        this.estudianteService.actualizarPorId(estudianteTo);
        return Response.status(Response.Status.OK).build();

    }

    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Actualizar parcialmente estudiante por ID", description = "Actualiza campos específicos de un estudiante existente por su ID.")

    public Response actualizarParcialPorId(@RequestBody EstudianteTo estudianteTo, @PathParam("id") Integer id) {
        estudianteTo.setId(id);
        EstudianteTo eTo = EstudianteMapper.toTo(this.estudianteService.buscarPorId(id));
        if (estudianteTo.getApellido() != null) {
            eTo.setApellido(estudianteTo.getApellido());
        }
        if (estudianteTo.getFechaNacimiento() != null) {
            eTo.setFechaNacimiento(estudianteTo.getFechaNacimiento());
        }
        if (estudianteTo.getNombre() != null) {
            eTo.setNombre(estudianteTo.getNombre());
        }
        this.estudianteService.actualizarParcialPorId((eTo));
        return Response.status(Response.Status.OK).build();
    }

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
