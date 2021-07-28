package com.example.wellD.controller;

import com.example.wellD.linesegment.PointLineSegmentsApplication;
import com.example.wellD.linesegment.calculation.LineSegment;
import com.example.wellD.linesegment.controller.LineSegmentController;
import com.example.wellD.linesegment.dto.SpacePoint;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LineSegmentController.class)
@ContextConfiguration(classes = {PointLineSegmentsApplication.class})
class LineSegmentControllerTest {

    @MockBean
    private LineSegment lineSegment;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldDeletePoints() throws Exception {
        doNothing().when(lineSegment).deletePoints();
        mockMvc.perform(delete("/api/remove"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetPoints() throws Exception {
        Set<SpacePoint> spacePointsMock = new HashSet<>();
        spacePointsMock.add(new SpacePoint(2, 0));
        spacePointsMock.add(new SpacePoint(1, 0));
        when(lineSegment.getAllPoints()).thenReturn(spacePointsMock);
        mockMvc.perform(get("/api/space"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(spacePointsMock)));
    }

    @Test
    void shouldAddPointsToSpace() throws Exception {
        SpacePoint spacePoint = new SpacePoint(2, 0);
        List<SpacePoint> spacePoints = new ArrayList<>();
        spacePoints.add(spacePoint);
        doNothing().when(lineSegment).addPointsToSpace(spacePoints);
        mockMvc.perform(post("/api/points")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(spacePoints)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldFetchLineSegmentPoints() throws Exception {
        Set<SpacePoint> SpacePointSet = new HashSet<>();
        SpacePointSet.add(new SpacePoint(0, 0));
        SpacePointSet.add(new SpacePoint(1, 1));
        SpacePointSet.add(new SpacePoint(2, 2));
        SpacePointSet.add(new SpacePoint(3, 3));
        List<Set<SpacePoint>> result = new ArrayList<>();
        result.add(SpacePointSet);
        Integer numberOfPoints = 4;
        when(lineSegment.findLineSegments(numberOfPoints)).thenReturn(result);
        mockMvc.perform(get("/api/lines/" + numberOfPoints))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(result)));
    }
}
