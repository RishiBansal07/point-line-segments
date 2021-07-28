package com.example.wellD.linesegment.calculation;

import com.example.wellD.linesegment.dto.SpacePoint;
import com.example.wellD.linesegment.exceptions.InvalidInputException;
import com.example.wellD.linesegment.repository.MockDBRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class LineSegment {

    private final MockDBRepository mockDBRepository;
    private final CalculationOfPoints calculationOfPoints;


    /**
     * Function to add points in the space
     * @param list
     */
    public void addPointsToSpace(List<SpacePoint> list) {
        mockDBRepository.addPointsToSpace(list);
    }

    /**
     * Determine the line segements passing through the N(user input) points
     * @param numberOfPoints
     * @return
     */
    public List<Set<SpacePoint>> findLineSegments(Integer numberOfPoints) {
        if (numberOfPoints<2){
            throw new InvalidInputException("Invalid input!! We need atleast two points to make a line segment.");
        }

        Set<SpacePoint> pointsInSpace = mockDBRepository.storePointsDB;

        // If number of points entered by user are greater than the points present in space
        if (pointsInSpace.size() < numberOfPoints) {
            throw new InvalidInputException("Insufficient points are added by user");
        }

        // if there are only two points present in space and user asked for the line segment passing through only two points
        if (pointsInSpace.size() == 2 && pointsInSpace.size() == numberOfPoints){
            List<Set<SpacePoint>> spacePoints = new ArrayList<>();
            spacePoints.add(pointsInSpace);
            return spacePoints;
        }

        int[][] points = transformUserInput(pointsInSpace);
        return calculationOfPoints.maximumNumberOfPoints(points, numberOfPoints);
    }

    /**
     * Convert the user input into suitable format which is used for business logic
     * @param pointsInSpace
     * @return
     */
    private int[][] transformUserInput(Set<SpacePoint> pointsInSpace) {
        Iterator<SpacePoint> pointIterator = pointsInSpace.iterator();
        int[][] points = new int[pointsInSpace.size()][2];
        int count = 0;
        while (pointIterator.hasNext()) {
            SpacePoint point = pointIterator.next();
            points[count][0] = point.getPointX();
            points[count][1] = point.getPointY();
            count++;
        }
        return points;
    }

    /**
     * To retrieve all the points entered by user
     * @return
     */
    public Set<SpacePoint> getAllPoints() {
        return mockDBRepository.storePointsDB;
    }

    /**
     * To delete all the points present in the space
     */
    public void deletePoints() {
        mockDBRepository.storePointsDB.clear();
    }
}
