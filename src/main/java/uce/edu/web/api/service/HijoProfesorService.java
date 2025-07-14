package uce.edu.web.api.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import uce.edu.web.api.repository.IHijoProfeRepo;
import uce.edu.web.api.repository.modelo.HijoProfesor;

@ApplicationScoped
public class HijoProfesorService implements IHijoProfesorService {

    @Inject
    private IHijoProfeRepo hijoProfeRepo;

    @Override
    public List<HijoProfesor> buscarPorProfesorId(Integer id) {
        return this.hijoProfeRepo.buscarPorProfesorId(id);

    }

}
