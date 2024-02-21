package com.uncledavecode.api_rest.dao.repositories;

import com.uncledavecode.api_rest.model.dtos.OrderDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OrderRepoTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgreSQLContainer
            = new PostgreSQLContainer<>("postgres:16.1");

    @Test
    void connectionTest() {
        assertTrue(postgreSQLContainer.isCreated());
        assertTrue(postgreSQLContainer.isRunning());
    }

    @Autowired
    private OrderRepo orderRepo;

    @Test
    void shouldFindOrderByOrderNumber() {
        // given
        String orderNumber = "ORD-0002";
        // when
        var order = orderRepo.findByOrderNumber(orderNumber);
        // then
        assertTrue(order.isPresent());
    }

    @Test
    void shouldNotFindOrderByOrderNumber() {
        // given
        String orderNumber = "ORD-00012313122";
        // when
        var order = orderRepo.findByOrderNumber(orderNumber);
        // then
        assertTrue(order.isEmpty());
    }

    @Test
    void shoulFindTwoOrderByDeliveryStatus() {
        // given
        String deliveryStatus = "DELIVERED";
        // when
        //filter,orderNumber,since,until,status, customerId, page, size
        List<OrderDTO> orders = orderRepo.findByFilter(
                4, null, null, null,
                deliveryStatus, null, 0, 10);
        // then
        assertEquals(2, orders.size());
    }

    @Test
    void shoulCountByDeliveryStatus() {
        // given
        String deliveryStatus = "DELIVERED";
        // when
        //filter,orderNumber,since,until,status, customerId, page, size
        int total = orderRepo.countFindByFilter(
                4, null, null, null,
                deliveryStatus, null);
        // then
        assertEquals(2, total);
    }
}