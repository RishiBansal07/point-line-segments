package com.example.wellD.linesegment.repository;

import com.example.wellD.linesegment.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
@Slf4j
public class MockDBRepository {

    public Set<SpacePoint> storePointsDB = new HashSet<>();

    public void addPointsToSpace(List<SpacePoint> spacePoint) {
        //log.info("Adding Points to space : (x,y) = ({}, {}) ", spacePoint.getPointX(), spacePoint.getPointY());
        storePointsDB.addAll(spacePoint);
    }

    public void getAllPointsFromSpace() {
        storePointsDB.toArray();
    }

    private void deleteAllPointsInSpace() {
        storePointsDB.clear();
    }
}
