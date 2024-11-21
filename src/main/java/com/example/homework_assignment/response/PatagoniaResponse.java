package com.example.homework_assignment.response;
import com.example.homework_assignment.models.Images;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class PatagoniaResponse {
    @JsonProperty("id")
    private String Id;
    @JsonProperty("images")
    private Images images;
    @JsonProperty("lat")
    private float lat;
    @JsonProperty("lng")
    private float lng;
    @JsonProperty("amenities")
    private List<String> amenities;
}
