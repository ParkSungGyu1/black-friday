package com.example.paymentservice.controller;

import com.example.paymentservice.dto.PaymentMethodDto;
import com.example.paymentservice.dto.PaymentMethodResponseDto;
import com.example.paymentservice.dto.PaymentResponseDto;
import com.example.paymentservice.dto.ProcessPaymentDto;
import com.example.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/payment/methods")
    public PaymentMethodResponseDto registerPaymentMethod(@RequestBody PaymentMethodDto dto) {
        return paymentService.registerPaymentMethod(
                dto.getUserId(),
                dto.getPaymentMethodType(),
                dto.getCreditCardNumber()
        );
    }

    @PostMapping("/payment/process-payment")
    public PaymentResponseDto processPayment(@RequestBody ProcessPaymentDto dto) throws Exception {
        return paymentService.processPayment(
                dto.getUserId(),
                dto.getOrderId(),
                dto.getAmountKRW(),
                dto.getPaymentMethodId()        );
    }

    @GetMapping("/payment/users/{userId}/first-method")
    public PaymentMethodResponseDto getPaymentMethod(@PathVariable Long userId) {
        return paymentService.getPaymentMethodByUser(userId);
    }
    @GetMapping("/payment/payments/{paymentId}")
    public PaymentResponseDto getPayment(@PathVariable Long paymentId) {
        return paymentService.getPayment(paymentId);
    }
}
