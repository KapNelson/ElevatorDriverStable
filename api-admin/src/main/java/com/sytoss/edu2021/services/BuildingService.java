package com.sytoss.edu2021.services;

import com.sytoss.edu2021.bom.BuildingBOM;
import com.sytoss.edu2021.exceptions.AlreadyExistsException;
import com.sytoss.edu2021.exceptions.EntityNotFoundException;
import com.sytoss.edu2021.exceptions.ValidationException;
import com.sytoss.edu2021.repo.BuildingRepository;
import com.sytoss.edu2021.repo.CabinRepository;
import com.sytoss.edu2021.repo.dto.BuildingDTO;
import com.sytoss.edu2021.services.convertor.BuildingConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BuildingService {

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private CabinRepository cabinRepository;

    @Autowired
    RestTemplate restTemplate;


/*    public BuildingBOM getBuildingById(int id) {
        BuildingDTO dto = buildingRepository.findBuildingById(id);
        if (dto == null) {
            throw new EntityNotFoundException(id, "There is no such building " + id);
        }
        BuildingBOM result = new BuildingBOM();
        new BuildingConvertor().fromDTO(dto, result);
        return result;
    }*/

    public BuildingBOM registerBuilding(BuildingBOM building) {
        if (building.isValid()) {
            try {
                BuildingBOM checkBuilding = searchByAddress(building.getAddress());
                throw new AlreadyExistsException("Building with this address already registered. BuildingId=" + checkBuilding.getId());
            } catch (EntityNotFoundException e) {
                BuildingDTO dto = new BuildingDTO();
                new BuildingConvertor().toDTO(building, dto);
                dto = buildingRepository.save(dto);
                new BuildingConvertor().fromDTO(dto, building);
                return building;
            }
        } else {
            throw new ValidationException("Data is not valid");
        }
    }


 /*   public BuildingBOM addCabin(int buildingId, CabinBOM cabin) {
        if (cabin.isValid()) {
            BuildingBOM building = getBuildingById(buildingId);
            if (building.findCabinByNumber(cabin.getNumber()) != null) {
                throw new ValidationException("Building id = " + buildingId + " already contains cabin with number = " + cabin.getNumber());
            } else {
                Integer[] cabinIds = building.getCabinIdList();

                EngineBOM[] engines = proxy.getEngines(cabinIds);

                for (int i = 0; i < building.getCabins().size(); i++) {
                    engines[i].setCabin(building.getCabins().get(i));
                }
                CabinDTO cabinDTO = new CabinDTO();
                new CabinConvertor().toDTO(cabin, cabinDTO);
                new CabinConvertor().toDTO(building, cabinDTO);
                cabinRepository.save(cabinDTO);
                building.addCabin(cabin);
                EngineBOM engineBOM = proxy.getEngine(cabinDTO.getId());
                engineBOM.setCabin(cabin);
                return building;
            }
        } else {
            throw new ValidationException("Invalid cabin number (number should be > 0)");
        }
    }*/


    private BuildingBOM searchByAddress(String address) {
        BuildingDTO building = buildingRepository.findBuildingByAddress(address);
        if (building != null) {
            BuildingBOM buildingBOM = new BuildingBOM();
            new BuildingConvertor().fromDTO(building, buildingBOM);
            return buildingBOM;
        } else {
            throw new EntityNotFoundException("There is no building on this address: " + address);
        }
    }


    /*public BuildingBOM searchById(Integer id) {
        BuildingDTO building = buildingRepository.findBuildingById(id);
        if (building != null) {
            BuildingBOM buildingBOM = new BuildingBOM();
            new BuildingConvertor().fromDTO(building, buildingBOM);
            return buildingBOM;
        } else {
            throw new EntityNotFoundException("There is no building on this id: " + id);
        }
    }*/
}