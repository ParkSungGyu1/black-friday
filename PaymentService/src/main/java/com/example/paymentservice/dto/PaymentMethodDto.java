package com.example.paymentservice.dto;

import com.example.paymentservice.enums.PaymentMethodType;
import lombok.Getter;

@Getter
public class PaymentMethodDto {
    private Long userId;
    private PaymentMethodType paymentMethodType;
    private String creditCardNumber;

}
