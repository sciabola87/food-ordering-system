package com.food.ordering.system.order.service.domain.entity;

import com.food.ordering.system.domain.entity.AggreateRoot;
import com.food.ordering.system.domain.valueobject.ResturantId;

import java.util.List;

public class Restaurant extends AggreateRoot<ResturantId> {

    private final List<Product> products;
    private boolean active;

    private Restaurant(Builder builder) {
        super.setId(builder.resturantId);
        products = builder.products;
        active = builder.active;
    }

    public static Builder builder() {
        return new Builder();
    }

    public List<Product> getProducts() {
        return products;
    }

    public boolean isActive() {
        return active;
    }

    public static final class Builder {
        private ResturantId resturantId;
        private List<Product> products;
        private boolean active;

        private Builder() {
        }

        public Builder id(ResturantId val) {
            resturantId = val;
            return this;
        }

        public Builder products(List<Product> val) {
            products = val;
            return this;
        }

        public Builder active(boolean val) {
            active = val;
            return this;
        }

        public Restaurant build() {
            return new Restaurant(this);
        }
    }
}
