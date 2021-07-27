package com.example.wellD.linesegment.calculation;

import com.example.wellD.linesegment.dto.LinePair;
import com.example.wellD.linesegment.dto.SpacePoint;
import com.example.wellD.linesegment.repository.MockDBRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class LineSegment {

    private final MockDBRepository mockDBRepository;
    private final CalculationOfPoints calculationOfPoints;


    public void addPointsToSpace(List<SpacePoint> list) {
        mockDBRepository.addPointsToSpace(list);
    }

    public List<Set<LinePair>> findLineSegments(Integer numberOfPoints) {
        Set<SpacePoint> pointsInSpace = mockDBRepository.storePointsDB;
        Iterator<SpacePoint> pointIterator = pointsInSpace.iterator();
        int[][] points = new int[pointsInSpace.size()][2];
        int count = 0;
        while (pointIterator.hasNext()) {
            SpacePoint point = pointIterator.next();
            points[count][0] = point.getPointX();
            points[count][1] = point.getPointY();
            count++;
        }
        return calculationOfPoints.maximumNumberOfPoints(points, numberOfPoints);
    }

    public Set<SpacePoint> getAllPoints() {
        return mockDBRepository.storePointsDB;
    }

    public void deletePoints() {
        mockDBRepository.storePointsDB.clear();
    }
}
