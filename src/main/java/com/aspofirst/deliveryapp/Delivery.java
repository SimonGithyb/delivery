package com.aspofirst.deliveryapp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Delivery {
    private int id;
    private String status;
    private String date;
    private String from; // address from start delivery
    private String zipCodeFrom;
    private String to; // addres where delivery is ended
    private String zipCodeTo;
}
