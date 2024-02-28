package com.project.cbstask.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class APIError {
    private String code;
    private String errorMessage;

    public APIError(String errorMessage) {
        this.code = "00";
        this.errorMessage = errorMessage;
    }
}
