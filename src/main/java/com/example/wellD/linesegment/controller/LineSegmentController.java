package com.example.wellD.linesegment.controller;

import com.example.wellD.linesegment.calculation.LineSegment;
import com.example.wellD.linesegment.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class LineSegmentController {

    private final LineSegment lineSegment;

    public LineSegmentController(LineSegment lineSegment) {
        this.lineSegment = lineSegment;
    }

    @PostMapping("/points")
    public ResponseEntity<String> addPointsToSpace(@RequestBody List<SpacePoint> listOfSpacePoints) {
        lineSegment.addPointsToSpace(listOfSpacePoints);
        return ResponseEntity.ok("Points Added to SPACE!");
    }

    @GetMapping("/space")
    public Set<SpacePoint> getPointsInSpace() {
        return lineSegment.getAllPoints();
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> removePoints() {
        lineSegment.deletePoints();
        return ResponseEntity.ok("Points deleted! Proceed to add a fresh new points!");
    }

    @GetMapping("/lines/{numberOfPoints}")
    public Object getPointsInSpace(@PathVariable Integer numberOfPoints) {
        return lineSegment.findLineSegments(numberOfPoints);
    }
}
