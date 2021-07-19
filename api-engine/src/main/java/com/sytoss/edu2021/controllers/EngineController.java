package com.sytoss.edu2021.controllers;

import com.sytoss.edu2021.repo.dto.EngineBOM;
import com.sytoss.edu2021.services.EngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/engine")
public class EngineController {

    @Autowired
    private EngineService engineService;

    @GetMapping("/{idCabin}")
    public EngineBOM create(@PathVariable Integer idCabin){
        return engineService.create(idCabin);
    }

    @PostMapping ("/engines/")
    public EngineBOM[] getEngines(@RequestBody Integer[] ids){
        return engineService.getEngines(ids);
    }

    @GetMapping ("/get/{idCabin}")
    public EngineBOM getEngine(@PathVariable Integer idCabin){
        return engineService.getEngine(idCabin);
    }

}
