package com.project.cbstask.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountName;
    @Column(unique = true, nullable = false, updatable = false)
    private String accountNumber;
    private String accountSchemeCode;
    @Column(nullable = false)
    private BigDecimal accountBalance;
    private Date dateCreated;
}
