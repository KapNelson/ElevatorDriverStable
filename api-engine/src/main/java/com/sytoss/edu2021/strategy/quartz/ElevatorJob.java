package com.sytoss.edu2021.strategy.quartz;

import com.sytoss.edu2021.bom.EngineBOM;
import com.sytoss.edu2021.repo.EngineRepository;
import com.sytoss.edu2021.repo.RouteRepository;
import com.sytoss.edu2021.repo.dto.EngineDTO;
import com.sytoss.edu2021.repo.dto.RouteDTO;
import com.sytoss.edu2021.repo.dto.RouteDTOId;
import com.sytoss.edu2021.services.convertor.EngineConvertor;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ElevatorJob implements Job {

    private List<EngineBOM> engineBOMS = new ArrayList<>();
    private RouteRepository routeRepository;
    private EngineRepository engineRepository;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        for (EngineBOM engine : engineBOMS) {
            RouteDTO[] routeDTOS = routeRepository.findAllByRouteDTOId_IdEngine(engine.getId());

            Set<Integer> set = new HashSet<>();
            for (RouteDTO route : routeDTOS) {
                set.add(route.getRouteDTOId().getFloorNumber());
            }

            if (set.isEmpty())
                continue;

            engine.getRoute().setQueueOfFloors(set);
            engine.getRoute().setDirection(engine.getCurrentFloor(), engine.getRoute().getMinValue());
            switch (engine.getStatus()) {
                case RUNNING_DOWN:
                case RUNNING_UP:
                    engine.move();
                    break;
                case STOP:
                    RouteDTOId removeRoute = new RouteDTOId();
                    removeRoute.setFloorNumber(engine.getCurrentFloor());
                    removeRoute.setIdEngine(engine.getId());

                    RouteDTO remove = new RouteDTO();
                    remove.setRouteDTOId(removeRoute);
                    RouteDTO check = routeRepository.findRouteDTOByRouteDTOId(removeRoute);
                    if (check != null) {
                        routeRepository.deleteByRouteDTOId(removeRoute);
                    }
                    if (!engine.getRoute().getQueueOfFloors().isEmpty()) {
                        engine.start();
                    }
                    break;
                case BROKEN:
                    break;
            }

            EngineDTO engineDTO = new EngineDTO();
            new EngineConvertor().toDTO(engine, engineDTO);
            engineRepository.save(engineDTO);
        }
    }

    public void setRouteRepository(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    public void setEngineRepository(EngineRepository engineRepository) {
        this.engineRepository = engineRepository;
    }

    public void setEngine(EngineBOM engineBOM) {
        engineBOMS.add(engineBOM);
    }

    public void setEngines(List<EngineBOM> engines) {
        this.engineBOMS = engines;
    }

}
