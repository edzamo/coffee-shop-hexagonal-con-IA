package com.ezamora.coffeeshop.infrastructure.adapter.out.persistence.order;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ezamora.coffeeshop.infrastructure.adapter.out.persistence.order.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    // Aquí puedes agregar métodos de consulta personalizados si los necesitas

    Optional<Order> findByUuid(UUID uuid);
}