package com.example.wellD.linesegment.calculation;

import com.example.wellD.linesegment.dto.*;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class Space {

    private Set<SpacePoint> pointsInSpace;
}
