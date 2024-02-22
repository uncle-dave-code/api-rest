package com.uncledavecode.api_rest.services;

import com.uncledavecode.api_rest.dao.repositories.CustomerRepo;
import com.uncledavecode.api_rest.dao.repositories.OrderRepo;
import com.uncledavecode.api_rest.model.dtos.OrderDTO;
import com.uncledavecode.api_rest.model.dtos.OrderResultDTO;
import com.uncledavecode.api_rest.model.dtos.PagingDataDTO;
import com.uncledavecode.api_rest.model.entities.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerRepo customerRepo;

    private final OrderRepo orderRepo;

    public OrderResultDTO getOrdersByFilter(int filter, String orderNumber,
                                            String since, String until,
                                            String status, String customerEmail,
                                            int page, int size) {

        //1-orderNumber, 2-order date, 3-delivery date, 4-status
        try {
            //Check if customer exists
            Customer customer = customerRepo.findByEmail(customerEmail).orElseThrow(
                    () -> new RuntimeException("Customer not found")
            );

            LocalDateTime sinceDate = null;
            LocalDateTime untilDate = null;

            try {
                if (filter == 2 || filter == 3) {
                    sinceDate = LocalDateTime.parse(since, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    untilDate = LocalDateTime.parse(until, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                }
            } catch (DateTimeParseException | NullPointerException e) {
                throw new RuntimeException("Invalid date format");
            }
            List<OrderDTO> orders = orderRepo.findByFilter(
                    filter, orderNumber, sinceDate, untilDate, status, customer.getId(), page, size);

            int total = orderRepo.countFindByFilter(
                    filter, orderNumber, sinceDate, untilDate, status, customer.getId());

            PagingDataDTO pagingData = new PagingDataDTO(page, size, total);

            return new OrderResultDTO(orders, pagingData);

        } catch (RuntimeException e) {
            return new OrderResultDTO("L001", e.getMessage());
        }
    }
}
