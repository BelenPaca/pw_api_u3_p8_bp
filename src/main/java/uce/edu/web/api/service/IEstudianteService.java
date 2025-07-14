package uce.edu.web.api.service;

import java.util.List;

import uce.edu.web.api.repository.modelo.Estudiante;
import uce.edu.web.api.service.to.EstudianteTo;

public interface IEstudianteService {

    public Estudiante buscarPorId(Integer id);
    public List<Estudiante> buscarTodos(String genero);
    public void actualizarPorId (EstudianteTo estudianteTo);
    public void actualizarParcialPorId (EstudianteTo estudianteTo);
    public void borrarPorId (Integer id);
    public void guardar (EstudianteTo estudianteTo);
}