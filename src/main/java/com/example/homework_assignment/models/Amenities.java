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
public class Amenities {
    List<String> general;
    List<String> room;
}
