package com.rajesh.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ORDER_TBL")
public class Order {
    @Id
    @GeneratedValue
    private long id;
    @Column
    private String item;
    @Column
    private int quantity;
    @Column
    private double amount;

    @Column
    private String status;

}
