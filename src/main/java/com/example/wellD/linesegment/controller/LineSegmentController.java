package com.example.wellD.linesegment.controller;

import com.example.wellD.linesegment.calculation.LineSegment;
import com.example.wellD.linesegment.dto.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


/**
 * Controller of WellD Patter Recognition project which controls the flow of point Line Segment part
 */
@RestController
@Slf4j
@RequestMapping("/api")
@Api(value = "WellD pattern recognition", tags = ("WELLD"))
public class LineSegmentController {

    private final LineSegment lineSegment;

    public LineSegmentController(LineSegment lineSegment) {
        this.lineSegment = lineSegment;
    }

    /*
    This rest end point will add user input points in the space.
     */
    @PostMapping(value = "/points")
    @ApiOperation(value = "Add points to the space !!! ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Technical error during elaboration")})
    public ResponseEntity<String> addPointsToSpace(@RequestBody List<SpacePoint> listOfSpacePoints) {
        log.info("Points added by user:" + listOfSpacePoints);
        lineSegment.addPointsToSpace(listOfSpacePoints);
        return ResponseEntity.ok("Points Added to SPACE!");
    }

    /*
    This rest end point will render all the points present in space.
    */
    @GetMapping(value = "/space")
    @ApiOperation(value = "Get all points added in space !!! ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Technical error during elaboration")})
    public Set<SpacePoint> getPointsInSpace() {
        log.info("Retrieving all the points present in space");
        return lineSegment.getAllPoints();
    }

    /*
    This rest end point will remove all the points present in space.
    */
    @DeleteMapping(value = "/remove")
    @ApiOperation(value = "Delte all points from the space !!! ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Technical error during elaboration")})
    public ResponseEntity<String> removePoints() {
        log.info("Deleting all the points present in space");
        lineSegment.deletePoints();
        return ResponseEntity.ok("Points deleted! Proceed to add a fresh new points!");
    }

    /*
    This rest end point will determine every line that contains N (input from user) or more points.
    */
    @GetMapping(value = "/lines/{numberOfPoints}")
    @ApiOperation(value = "Get all line segments passing through at least N points !!! ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Technical error during elaboration")})
    public Object getLineSegments(@PathVariable Integer numberOfPoints) {
        log.info("Retrieving the line segments through: " + numberOfPoints);
        return lineSegment.findLineSegments(numberOfPoints);
    }
}
