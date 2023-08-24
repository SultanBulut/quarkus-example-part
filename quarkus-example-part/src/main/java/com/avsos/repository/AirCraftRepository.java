package com.avsos.repository;

import com.avsos.entity.AirCraft;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AirCraftRepository implements PanacheRepository<AirCraft> {
}
