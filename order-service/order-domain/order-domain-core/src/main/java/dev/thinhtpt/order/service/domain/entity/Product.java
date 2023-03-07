package dev.thinhtpt.order.service.domain.entity;

import dev.thinhtpt.domain.entity.BaseEntity;
import dev.thinhtpt.domain.valueobject.Money;
import dev.thinhtpt.domain.valueobject.ProductId;

public class Product extends BaseEntity<ProductId> {
    private final String name;
    private final Money price;

    public Product(ProductId productId, String name, Money price) {
        super.setId(productId);
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Money getPrice() {
        return price;
    }
}
