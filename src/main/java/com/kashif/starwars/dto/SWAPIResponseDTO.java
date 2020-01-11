package com.kashif.starwars.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SWAPIResponseDTO {
    private int count;
    private String prev;
    private String next;
    private List<Result> results;
}

