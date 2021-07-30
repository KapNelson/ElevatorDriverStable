package com.sytoss.edu2021.controllers;

import com.sytoss.edu2021.bom.EngineBOM;
import com.sytoss.edu2021.bom.RouteBOM;
import com.sytoss.edu2021.services.EngineService;
import com.sytoss.edu2021.services.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/engine")
public class EngineController {

    @Autowired
    private EngineService engineService;
    @Autowired
    private RouteService routeService;

    @Value("${waitingStrategy.type}")
    private String type;
    @Value("${waitingStrategy.time}")
    private long waitTime;

    @PostMapping("/add/{buildingId}/{cabinId}")
    private EngineBOM registerEngine(@PathVariable Integer buildingId, @PathVariable Integer cabinId) {
        return engineService.addEngine(buildingId, cabinId);
    }

    @PostMapping("/add/route/{buildingId}/{cabinNumber}/{floorNumber}")
    public RouteBOM addFloorToRoute(@PathVariable Integer buildingId, @PathVariable Integer cabinNumber, @PathVariable Integer floorNumber) {
        return routeService.addFloorToRoute(buildingId, cabinNumber, floorNumber);
    }

    @PostMapping("/start/{buildingId}/{cabinNumber}")
    public void startMovement(@PathVariable Integer buildingId, @PathVariable Integer cabinNumber) {
        engineService.startMovement(buildingId, cabinNumber, type, waitTime);
    }

    @PostMapping("/startAllEngines")
    public void startMovement() {
        engineService.startMovement(type, waitTime);
    }

    @GetMapping("/get/{engineId}")
    public EngineBOM getEngine(@PathVariable Integer engineId) {
        return engineService.getEngine(engineId);
    }

    @GetMapping("/getTest")
    private String test() {
        return type;
    }
}
