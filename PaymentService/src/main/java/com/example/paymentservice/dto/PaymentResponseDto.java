package com.example.paymentservice.dto;

import com.example.paymentservice.enums.PaymentMethodType;
import com.example.paymentservice.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PaymentResponseDto {
    private Long id;
    private Long userId;
    private Long orderId;
    private Long amountKRW;
    private PaymentMethodType paymentMethodType;
    private String paymentData;
    private PaymentStatus paymentStatus;
    private Long referenceCode;
}
