package com.example.wellD.utils;
import com.example.wellD.linesegment.dto.SpacePoint;

import java.util.ArrayList;
import java.util.List;

public class DataRequired {

    public static List<SpacePoint> pointsData() {
        SpacePoint spacePoint = new SpacePoint(2, 0);
        List<SpacePoint> spacePoints = new ArrayList<>();
        spacePoints.add(spacePoint);
        return spacePoints;
    }
}
