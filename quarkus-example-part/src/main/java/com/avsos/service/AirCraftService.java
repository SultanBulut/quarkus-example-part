package com.avsos.service;


import com.avsos.dto.AirCraftDTO;
import com.avsos.entity.AirCraft;
import com.avsos.repository.AirCraftRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class AirCraftService {
    @Inject
    AirCraftRepository airCraftRepository;

    @Transactional
    public boolean createAirCraft(AirCraftDTO airCraftDTO){
        AirCraft airCraft =new AirCraft(airCraftDTO.getAcType(),airCraftDTO.getSubtype(),airCraftDTO.getCustomizedType(),airCraftDTO.getNameOfAircraft(),airCraftDTO.getNumberOfSeats(),airCraftDTO.getRegistrar(),airCraftDTO.getOwner(),airCraftDTO.getContract());
        airCraftRepository.persist(airCraft);
        return airCraftRepository.isPersistent(airCraft);
    }

    public List<AirCraft> getALLAirCraft(){
        return airCraftRepository.findAll().list();
    }
}
