package com.example.paymentservice.service;

import com.example.paymentservice.dto.PaymentMethodResponseDto;
import com.example.paymentservice.dto.PaymentResponseDto;
import com.example.paymentservice.entity.Payment;
import com.example.paymentservice.entity.PaymentMethod;
import com.example.paymentservice.enums.PaymentMethodType;
import com.example.paymentservice.enums.PaymentStatus;
import com.example.paymentservice.pg.CreditCardPaymentAdapter;
import com.example.paymentservice.repository.PaymentMethodRepository;
import com.example.paymentservice.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentMethodRepository paymentMethodRepository;
    private final PaymentRepository paymentRepository;
    private final CreditCardPaymentAdapter creditCardPaymentAdapter;

    public PaymentMethodResponseDto registerPaymentMethod(
            Long userId,
            PaymentMethodType paymentMethodType,
            String creditCardNumber
    ){
        var paymentMethod = new PaymentMethod(userId, paymentMethodType, creditCardNumber);
        PaymentMethod save = paymentMethodRepository.save(paymentMethod);
        return new PaymentMethodResponseDto(
                save.getId(),
                save.getUserId(),
                save.getPaymentMethodType(),
                save.getCreditCardNumber()
        );
    }

    public PaymentResponseDto processPayment(
            Long userId,
            Long orderId,
            Long amountKRW,
            Long paymentMethodId
    ){
        var paymentMethod = paymentMethodRepository.findById(paymentMethodId).orElseThrow();
        if(paymentMethod.getPaymentMethodType() != PaymentMethodType.CREDIT_CARD){
            throw new IllegalArgumentException("Unsupported payment method type");
        }
        //실제 결제 진행
        Long refCode = creditCardPaymentAdapter.processCreditPayment(amountKRW, paymentMethod.getCreditCardNumber());

        var payment = new Payment(
                userId,
                orderId,
                amountKRW,
                paymentMethod.getPaymentMethodType(),
                paymentMethod.getCreditCardNumber(),
                PaymentStatus.COMPLETED,
                refCode
        );

        Payment save = paymentRepository.save(payment);

        return new PaymentResponseDto(
                save.getId(),
                save.getUserId(),
                save.getOrderId(),
                save.getAmountKRW(),
                save.getPaymentMethodType(),
                save.getPaymentData(),
                save.getPaymentStatus(),
                save.getReferenceCode()
        );
    }

    public PaymentMethodResponseDto getPaymentMethodByUser(Long userId){
        PaymentMethod paymentMethod = paymentMethodRepository.findByUserId(userId).stream().findFirst().orElseThrow();
        return new PaymentMethodResponseDto(
                paymentMethod.getId(),
                paymentMethod.getUserId(),
                paymentMethod.getPaymentMethodType(),
                paymentMethod.getCreditCardNumber()
        );
    }

    public PaymentResponseDto getPayment(Long paymentId){
        Payment payment = paymentRepository.findById(paymentId).orElseThrow();
        return new PaymentResponseDto(
                payment.getId(),
                payment.getUserId(),
                payment.getOrderId(),
                payment.getAmountKRW(),
                payment.getPaymentMethodType(),
                payment.getPaymentData(),
                payment.getPaymentStatus(),
                payment.getReferenceCode()
        );
    }


}
