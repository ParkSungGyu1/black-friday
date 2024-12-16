package com.example.paymentservice.dto;

import lombok.Getter;

@Getter
public class ProcessPaymentDto {
    private Long userId;
    private Long orderId;
    private Long amountKRW;
    private Long paymentMethodId;
}
