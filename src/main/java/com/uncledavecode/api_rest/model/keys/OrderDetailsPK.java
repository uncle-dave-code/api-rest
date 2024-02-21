package com.uncledavecode.api_rest.model.keys;

import com.uncledavecode.api_rest.model.entities.Order;
import com.uncledavecode.api_rest.model.entities.Product;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class OrderDetailsPK implements Serializable {

    private Order order;

    private Product product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderDetailsPK that = (OrderDetailsPK) o;

        if (!order.equals(that.order)) return false;
        return product.equals(that.product);
    }

    @Override
    public int hashCode() {
        int result = order.hashCode();
        result = 31 * result + product.hashCode();
        return result;
    }
}
