package com.uncledavecode.api_rest.dao.repositories;

import com.uncledavecode.api_rest.model.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepo extends JpaRepository<Customer, Long> {

    Optional<Customer> findByEmail(String email);

}
