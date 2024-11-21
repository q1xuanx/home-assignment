package com.example.homework_assignment.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class AcmeResponse {
    @JsonProperty("Id")
    private String Id;
    @JsonProperty("City")
    private String City;
    @JsonProperty("Description")
    private String Description;
    @JsonProperty("Facilities")
    private List<String> Facilities;
}
