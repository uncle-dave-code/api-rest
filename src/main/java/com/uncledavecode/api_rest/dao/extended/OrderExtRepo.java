package com.uncledavecode.api_rest.dao.extended;

import com.uncledavecode.api_rest.model.dtos.OrderDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderExtRepo {

    List<OrderDTO> findByFilter(
            int filter, String orderNumber,
            LocalDateTime since, LocalDateTime until,
            String status, Long customerId,
            int page, int size);

    int countFindByFilter(
            int filter, String orderNumber,
            LocalDateTime since, LocalDateTime until,
            String status, Long customerId);
}
