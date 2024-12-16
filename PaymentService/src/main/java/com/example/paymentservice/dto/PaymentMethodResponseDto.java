package com.example.paymentservice.dto;

import com.example.paymentservice.enums.PaymentMethodType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PaymentMethodResponseDto {
    private Long id;
    private Long userId;
    private PaymentMethodType paymentMethodType;
    private String creditCardNumber;
}
