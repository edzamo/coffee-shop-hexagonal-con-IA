package com.ezamora.coffeeshop.infrastructure.adapter.out.persistence.order;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ezamora.coffeeshop.application.out.OrderNotFound;
import com.ezamora.coffeeshop.application.out.Orders;
import com.ezamora.coffeeshop.domain.model.order.Order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceAdapter implements Orders {

    private final OrderRepository orderRepository;

    @Override
    public Order findOrderById(UUID orderId) throws OrderNotFound {
        log.info("Finding OrderServiceAdapter with ID: {}", orderId);
        return orderRepository.findByUuid(orderId)
                .map(OrderMapper::toDomain)
                .orElseThrow(() -> new OrderNotFound("Order not found with id: " + orderId));
    }

    @Override
    public Order save(Order order) {
       
        var newEntityState = OrderMapper.toEntity(order);

        if (order.getId() != null) {
            var existingEntity = orderRepository.findByUuid(order.getId())
                    .orElseThrow(() -> new OrderNotFound("Cannot update an order that does not exist: " + order.getId()));

            // Preserva el ID interno de la BD para asegurar un UPDATE, no un INSERT.
            newEntityState.setId(existingEntity.getId());
            // Preserva la fecha de creación original, ya que no debería cambiar.
            newEntityState.setOrderDate(existingEntity.getOrderDate());
        }

        var savedEntity = orderRepository.save(newEntityState);
        return OrderMapper.toDomain(savedEntity);
    }

    @Override
    public void deleteById(UUID orderId) {
        // Primero debemos encontrar la entidad por su UUID público, ya que el método
        // deleteById del repositorio funciona con la clave primaria interna (Long).
        var orderEntity = orderRepository.findByUuid(orderId)
                .orElseThrow(() -> new OrderNotFound("Cannot delete an order that does not exist: " + orderId));
        orderRepository.delete(orderEntity);
    }

}
