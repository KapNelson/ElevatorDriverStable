package com.sytoss.edu2021.services;

import com.sytoss.edu2021.bom.BuildingBOM;
import com.sytoss.edu2021.bom.CabinBOM;
import com.sytoss.edu2021.controllers.FeignProxyEngine;
import com.sytoss.edu2021.exceptions.AlreadyExistsException;
import com.sytoss.edu2021.exceptions.EntityNotFoundException;
import com.sytoss.edu2021.exceptions.ValidationException;
import com.sytoss.edu2021.repo.BuildingRepository;
import com.sytoss.edu2021.repo.CabinRepository;
import com.sytoss.edu2021.repo.dto.BuildingDTO;
import com.sytoss.edu2021.repo.dto.CabinDTO;
import com.sytoss.edu2021.services.convertor.BuildingConvertor;
import com.sytoss.edu2021.services.convertor.CabinConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CabinService {

    @Autowired
    private CabinRepository cabinRepository;

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private FeignProxyEngine feignProxyEngine;

    public CabinBOM addCabin(Integer buildingId, CabinBOM cabin) {
        BuildingDTO buildingDTO = buildingRepository.findBuildingById(buildingId);
        if (buildingDTO == null) {
            throw new EntityNotFoundException("There is no building with id= " + buildingId + ".\nYou can not add a cabin.");
        }
        CabinDTO checkCabin = cabinRepository.findCabinDTOByBuildingIdAndNumber(buildingId,cabin.getNumber());
        if(checkCabin != null){
            throw new AlreadyExistsException("Such cabin already exists");
        }
        if (cabin.isValid()) {
            CabinDTO cabinDTO = new CabinDTO();
            cabinDTO.setBuildingId(buildingId);
            cabinDTO.setNumber(cabin.getNumber());
            cabinDTO = cabinRepository.save(cabinDTO);
            new CabinConvertor().fromDTO(cabinDTO,cabin);
            feignProxyEngine.registerEngine(buildingId,cabin.getId());
            return cabin;
        } else {
            throw new ValidationException("The cabin is invalid");
        }
    }

    private BuildingBOM findBuilding(Integer buildingId) {
        BuildingDTO buildingDTO = buildingRepository.findBuildingById(buildingId);
        if (buildingDTO == null) {
            throw new EntityNotFoundException("There is no building with id= " + buildingId + ".\nYou can not add a cabin.");
        }
        BuildingBOM buildingBOM = new BuildingBOM();
        new BuildingConvertor().fromDTO(buildingDTO, buildingBOM);
        return buildingBOM;
    }

    public CabinBOM getCabin(int buildingId, int cabinNumber) {
        CabinDTO cabinDTO = cabinRepository.findCabinDTOByBuildingIdAndNumber(buildingId,cabinNumber);
        if(cabinDTO == null)
            throw  new EntityNotFoundException("There is no such engine with buildingId: "+buildingId+" and cabinNumber: "+cabinNumber);
        CabinBOM cabinBOM = new CabinBOM();
        new CabinConvertor().fromDTO(cabinDTO,cabinBOM);
        return cabinBOM;
    }
}