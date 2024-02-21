package com.uncledavecode.api_rest.model.dtos;

import com.uncledavecode.api_rest.model.entities.Customer;

public record CustomerDTO(
        long id,
        String name,
        String email,
        String phone
) {
    public static CustomerDTO from(Customer customer) {
        return new CustomerDTO(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getPhone()
        );
    }
}
