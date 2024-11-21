package com.example.homework_assignment.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Images {
    List<Details> rooms;
    List<Details> site;
    List<Details> amenities;
}
