package com.uncledavecode.api_rest.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BaseResultDTO implements Serializable {
    private String code = "0";
    private String message = "";
}
