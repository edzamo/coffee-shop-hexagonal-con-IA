package com.ezamora.coffeeshop.infrastructure.adapter.out.persistence.payment;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ezamora.coffeeshop.infrastructure.adapter.out.persistence.payment.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    // Aquí puedes agregar métodos de consulta personalizados si los necesitas
}