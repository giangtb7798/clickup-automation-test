package org.example.microservices.models.spacemodels;

import lombok.Data;

import java.util.List;
@Data
public class SpacesResponseList {
    List<SpaceResponse> spaces;
}
