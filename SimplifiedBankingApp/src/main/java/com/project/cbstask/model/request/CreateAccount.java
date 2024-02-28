package com.project.cbstask.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAccount {
    private String accountName;
    private String accountSchemeCode;
}
