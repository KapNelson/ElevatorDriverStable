package com.sytoss.edu2021.services;

import com.sytoss.edu2021.common.RouteBOM;
import com.sytoss.edu2021.repo.BuildingRepository;
import com.sytoss.edu2021.repo.dto.BuildingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteService {
    /*@Autowired
    private RouteRepository routeRepository;
    @Autowired
    private EngineRepository engineRepository;

    @Autowired
    private BuildingRepository buildingRepository;

    public RouteBOM add(Integer buildingId, Integer cabinNumber, Integer floorNumber) {

        BuildingDTO checkBuilding = buildingRepository.findBuildingById(buildingId);
        if (checkBuilding == null) {
            throw new EntityNotFoundException("There is no building with id= " + buildingId + ".");
        }

        if (floorNumber > checkBuilding.getFloorsAmount()) {
            throw new ValidationException("There is no floor with number= " + floorNumber + " in this building");
        }

        EngineDTO engineDTO = engineRepository.findEngineDTOByBuildingIdAndCabinNumber(buildingId, cabinNumber);
        if (engineDTO == null) {
            throw new EntityNotFoundException("There is no cabin with number= " + cabinNumber +
                    " in building with id= " + buildingId + ".");
        }

        RouteDTOId routeDTOId = new RouteDTOId();
        routeDTOId.setFloorNumber(floorNumber);
        routeDTOId.setIdEngine(engineDTO.getId());
        RouteDTO checkRoute = routeRepository.findRouteDTOByRouteDTOId(routeDTOId);
        if (checkRoute != null) {
            throw new AlreadyExistsException("This point is already in route");
        } else {
            RouteDTO routeDTO = new RouteDTO();
            routeDTO.setRouteDTOId(routeDTOId);
            routeRepository.save(routeDTO);
        }
        RouteDTO[] routeDTOS = routeRepository.findAllByRouteDTOId_IdEngine(routeDTOId.getIdEngine());
        RouteBOM route = new RouteBOM();
        new RouteConvertor().fromDTO(routeDTOS, route);
        return route;
    }
*/

}
