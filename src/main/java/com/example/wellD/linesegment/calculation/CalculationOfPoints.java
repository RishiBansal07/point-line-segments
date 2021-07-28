package com.example.wellD.linesegment.calculation;

import com.example.wellD.linesegment.dto.SpacePoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

/*
This class will find the points of the line segments passing through the N (entered by user) points
 */

@Slf4j
@Service
public class CalculationOfPoints {

    public List<Set<SpacePoint>> maximumNumberOfPoints(int[][] points, int numberOfPoints) {
        log.info("Getting all line segment points passing through at least: " + numberOfPoints + " points");

        Map<String, Integer> noOfPointsWithSameSlopeMap = new HashMap<>();
        Map<String, Set<SpacePoint>> pointsWithSameSlopeMap = new HashMap<>();
        int n = points.length;
        for (int i = 0; i < n - 1; i++) {

            Map<String, Integer> map = new HashMap<>();
            SpacePoint pairX = new SpacePoint(points[i][0], points[i][1]);
            String nextKey = "";
            for (int j = i + 1; j < n; j++) {

                String finalKey = slopeCalculator(points[i], points[j]);

                if (finalKey == null) continue;

                if (nextKey != finalKey) {
                    nextKey = finalKey;
                }

                int counter = 1;
                SpacePoint pointPair = new SpacePoint(points[j][0], points[j][1]);
                Set<SpacePoint> pointSet = new HashSet<>();
                pointSet.add(pairX);
                pointSet.add(pointPair);
                if (noOfPointsWithSameSlopeMap.containsKey(finalKey)) {
                    counter = counter + noOfPointsWithSameSlopeMap.get(finalKey);
                    for (SpacePoint pointsf : pointsWithSameSlopeMap.get(finalKey)) {
                        pointSet.add(pointsf);
                    }
                }
                noOfPointsWithSameSlopeMap.put(nextKey, counter);
                pointsWithSameSlopeMap.put(nextKey, pointSet);
            }

            log.info(map + " Considering origin point : (" + points[i][0] + "," + points[i][1] + ")");
            log.info("=========Map with slope (containing slope + origin point) and number of points with same slope"
                    + noOfPointsWithSameSlopeMap);
            log.info("=========Map with slope (containing slope + origin point) and set of points with same slope" +
                    "" + pointsWithSameSlopeMap);
        }

        return pointsOnLine(numberOfPoints, noOfPointsWithSameSlopeMap, pointsWithSameSlopeMap);
    }

    /**
     *
     * This method is returning the points of line segment passing through N points
     * @param numberOfPoints
     * @param noPointsWithSameSlopeMap
     * @param pointsWithSameSlopeMap
     * @return
     */
    private List<Set<SpacePoint>> pointsOnLine(int numberOfPoints, Map<String, Integer> noPointsWithSameSlopeMap, Map<String, Set<SpacePoint>> pointsWithSameSlopeMap) {
        List<Set<SpacePoint>> SpacePoints = new ArrayList<>();
        List<String> returnPts = new ArrayList<>();
        for (String inputKey : noPointsWithSameSlopeMap.keySet()) {
            if (noPointsWithSameSlopeMap.get(inputKey) >= (numberOfPoints - 1)) {
                returnPts.add(inputKey);
            }
        }
        for (String keyMap : returnPts) {
            log.info("=====Line segment points" + pointsWithSameSlopeMap.get(keyMap));
            SpacePoints.add(pointsWithSameSlopeMap.get(keyMap));
        }
        return SpacePoints;
    }

    /**
     * This method will calculate the slope of the points
     * @param point
     * @param point1
     * @return
     */
    private String slopeCalculator(int[] point, int[] point1) {
        int x = point[0] - point1[0];
        int y = point[1] - point1[1];
        if (x == 0 && y == 0) {
            return null;
        }
        // divide by gcd to reduce the factor in slope
        int gcd = gcd(x, y);
        x /= gcd;
        y /= gcd;
        String finalKey = "(" + point[0] + "," + point[1] + ")" + y + "/" + x;
        return finalKey;
    }

    /**
     * Function to calculate the Greatest Common Divisor (GCD)
     * @param x
     * @param y
     * @return
     */
    private int gcd(int x, int y) {
        if (y == 0) return x;
        return gcd(y, x % y);
    }
}