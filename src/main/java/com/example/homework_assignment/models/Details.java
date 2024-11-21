package com.example.homework_assignment.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Details {
    @JsonAlias({"url", "link"})
    private String link;
    @JsonAlias({"description", "caption"})
    private String description;
}
