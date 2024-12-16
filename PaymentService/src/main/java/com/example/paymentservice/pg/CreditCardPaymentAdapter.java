package com.example.paymentservice.pg;

import org.springframework.stereotype.Component;

@Component
public interface CreditCardPaymentAdapter {
    Long processCreditPayment(Long amountKRW, String creditCardNumber);
}
