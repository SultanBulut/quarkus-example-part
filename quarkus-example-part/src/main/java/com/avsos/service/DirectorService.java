package com.avsos.service;

import com.avsos.repository.DirectorRepository;
import com.avsos.entity.Director;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class DirectorService {
    @Inject
    DirectorRepository directorRepository;

    @Transactional
    public boolean createDirector(Director director){
        Director newdirector = new Director(director.getName(),director.getLastname());
        directorRepository.persist(newdirector);
        return directorRepository.isPersistent(newdirector);

    }

    public List<Director> getAllDirectors(){
        return directorRepository.findAll().list();
    }
}
