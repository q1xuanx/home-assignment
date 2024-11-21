package com.example.homework_assignment.models;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Hotel {
    private String hotel_id;
    private String destination_id;
    private String hotel_name;
    private Location location;
    @JsonProperty("details")
    private String description;
    private Amenities amenities;
    private Images images;
    private List<String> booking_conditions;
}
