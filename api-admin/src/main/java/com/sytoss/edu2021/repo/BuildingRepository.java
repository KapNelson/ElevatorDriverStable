package com.sytoss.edu2021.repo;

import com.sytoss.edu2021.repo.dto.BuildingDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingRepository extends JpaRepository<BuildingDTO, Integer> {
    BuildingDTO findBuildingByAddress(String address);
    BuildingDTO findBuildingById(Integer idBuilding);
}