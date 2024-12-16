package com.example.paymentservice.pg;

import org.springframework.stereotype.Service;

@Service
public class EasyCreditCardPaymentAdapter implements CreditCardPaymentAdapter{
    @Override
    public Long processCreditPayment(Long amountKRW, String creditCardNumber) {
        // actual process with creditCard Payment external System
        return Math.round(Math.random() * 100000000);
    }
}
