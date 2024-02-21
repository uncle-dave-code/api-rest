package com.uncledavecode.api_rest.dao.extended.impl;

import com.uncledavecode.api_rest.dao.extended.OrderExtRepo;
import com.uncledavecode.api_rest.model.dtos.OrderDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderExtRepoImpl implements OrderExtRepo {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    public List<OrderDTO> findByFilter(
            int filter, String orderNumber,
            LocalDateTime since, LocalDateTime until,
            String status, Long customerId,
            int page, int size) {
        String query = """
                select
                    ord.id as id,
                    ord.orderNumber as orderNumber,
                    ord.orderDate as orderDate,
                    ord.deliveryDate as deliveryDate,
                    ord.status as status,
                    ord.customer as customer
                from
                    Order ord
                where
                    (:filter = 1 and ord.orderNumber = :orderNumber) or
                    (:filter = 2 and ord.orderDate between :since and :until) or
                    (:filter = 3 and ord.deliveryDate between :since and :until) or
                    (:filter = 4 and ord.status = :status) or
                    (:filter = 5 and ord.customer.id = :customerId)
                """;
        TypedQuery<OrderDTO> typedQuery = entityManager.createQuery(query, OrderDTO.class)
                .unwrap(Query.class)
                .setTupleTransformer(OrderDTO::fromFields);

        typedQuery.setParameter("filter", filter);
        typedQuery.setParameter("orderNumber", orderNumber);
        typedQuery.setParameter("since", since);
        typedQuery.setParameter("until", until);
        typedQuery.setParameter("status", status);
        typedQuery.setParameter("customerId", customerId);

        typedQuery.setFirstResult(page * size);
        typedQuery.setMaxResults(size);

        return typedQuery.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public int countFindByFilter(
            int filter, String orderNumber,
            LocalDateTime since, LocalDateTime until,
            String status, Long customerId) {
        String query = """
                select
                    count(ord.id) as count
                from
                    Order ord
                where
                    (:filter = 1 and ord.orderNumber = :orderNumber) or
                    (:filter = 2 and ord.orderDate between :since and :until) or
                    (:filter = 3 and ord.deliveryDate between :since and :until) or
                    (:filter = 4 and ord.status = :status) or
                    (:filter = 5 and ord.customer.id = :customerId)
                """;
        TypedQuery<Long> typedQuery = entityManager.createQuery(query, Long.class)
                .unwrap(Query.class);

        typedQuery.setParameter("filter", filter);
        typedQuery.setParameter("orderNumber", orderNumber);
        typedQuery.setParameter("since", since);
        typedQuery.setParameter("until", until);
        typedQuery.setParameter("status", status);
        typedQuery.setParameter("customerId", customerId);

        return typedQuery.getSingleResult().intValue();

    }
}
