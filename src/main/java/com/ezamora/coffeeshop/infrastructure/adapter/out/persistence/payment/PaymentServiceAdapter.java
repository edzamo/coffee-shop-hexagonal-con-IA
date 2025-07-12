package com.ezamora.coffeeshop.infrastructure.adapter.out.persistence.payment;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ezamora.coffeeshop.application.out.Payments;
import com.ezamora.coffeeshop.domain.model.payment.Payment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceAdapter implements Payments {

    @Override
    public Payment findPaymentByOrderId(UUID orderId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findPaymentByOrderId'");
    }

    @Override
    public Payment save(Payment payment) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

}
