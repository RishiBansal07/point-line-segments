package com.example.wellD.linesegment.calculation;

import com.example.wellD.linesegment.dto.LinePair;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CalculationOfPoints {

    public List<Set<LinePair>> maximumNumberOfPoints(int[][] points, int numberOfPoints) {
        Map<String, Integer> finalMap = new HashMap<>();
        Map<String, Set<LinePair>> finalPoints = new HashMap<>();
        //if (points.length <= 2) return points.length;
        int n = points.length;
        List<Set<LinePair>> linePairs = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {

            Map<String, Integer> map = new HashMap<>();
            LinePair pairX = new LinePair(points[i][0], points[i][1]);
            String nextKey = "";
            for (int j = i + 1; j < n; j++) {

                int x = points[i][0] - points[j][0];
                int y = points[i][1] - points[j][1];
                if (x == 0 && y == 0) {
                    continue;
                }
                // divide by gcd to reduce the factor in slope
                int gcd = gcd(x, y);
                x /= gcd;
                y /= gcd;
                String key = y + "/" + x;
                String finalKey = "(" + points[i][0] + "," + points[i][1] + ")" + y + "/" + x;
                LinePair pointPair = new LinePair(points[j][0], points[j][1]);

                    if (nextKey != finalKey) {
                        nextKey = finalKey;
                    }
                    int value = 1;
                    Set<LinePair> pointSet = new HashSet<LinePair>();
                    pointSet.add(pairX);
                    pointSet.add(pointPair);
                    if (finalMap.containsKey(finalKey)) {
                        value = value + finalMap.get(finalKey);
                        for (LinePair pointsf : finalPoints.get(finalKey)) {
                            pointSet.add(pointsf);
                        }
                    }
                    finalMap.put(nextKey, value);
                    finalPoints.put(nextKey, pointSet);
            }

            System.out.println(map + " Considering origin point : (" + points[i][0] + "," + points[i][1] + ")");
            System.out.println("=========Our Map" + finalMap);
            System.out.println("=========Our Points" + finalPoints);
        }

        List<String> returnPts = new ArrayList<>();
        for (String inputKey : finalMap.keySet()) {
            if (finalMap.get(inputKey) >= (numberOfPoints-1)) {
                returnPts.add(inputKey);
            }
        }
        for (String keyMap : returnPts) {
            System.out.println("=====final points" + finalPoints.get(keyMap));
            linePairs.add(finalPoints.get(keyMap));
        }
        return linePairs;
    }

    private static int gcd(int x, int y) {
        if (y == 0) return x;
        return gcd(y, x % y);
    }
}