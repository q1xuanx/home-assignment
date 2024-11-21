package com.example.homework_assignment.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Location {
    private float lat;
    private float lng;
    private String address;
    private String city;
    private String country;
}
