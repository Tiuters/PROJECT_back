package com.warehouse_accounting.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "adjustments")
public class Adjustment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String number;

    @Column
    private LocalDateTime dateTimeAdjustment;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    private Contractor contractor;

    @Column
    @Enumerated(EnumType.STRING)
    private TypeOfAdjustment type;

    @Column(scale = 2)
    private BigDecimal currentBalance = BigDecimal.valueOf(0);

    @Column(scale = 2)
    private BigDecimal totalBalance = BigDecimal.valueOf(0);

    @Column(scale = 2)
    private BigDecimal adjustmentAmount = BigDecimal.valueOf(0);

    @Column
    private String comment;

    @Column
    private LocalDateTime whenСhanged;
}
