package com.kashif.starwars.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {
    private String type;
    private int count;
    private String name;
    private List<Map> films;
}
