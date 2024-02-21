package com.uncledavecode.api_rest.dao.repositories;

import com.uncledavecode.api_rest.dao.extended.OrderExtRepo;
import com.uncledavecode.api_rest.model.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepo extends JpaRepository<Order, Long>, OrderExtRepo {

    Optional<Order> findByOrderNumber(String orderNumber);
}
