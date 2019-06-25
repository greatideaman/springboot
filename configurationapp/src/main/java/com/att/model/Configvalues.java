package com.att.model;

import javax.persistence.*;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Model
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CONFIGVALUES")
@Entity
public class Configvalues {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int configID;

    @Column(nullable = false)
    private String configName; // 'A, B, C, D' etc

    @Column(nullable = false)
    private String configDate; // 022019 for Feb 2019
}