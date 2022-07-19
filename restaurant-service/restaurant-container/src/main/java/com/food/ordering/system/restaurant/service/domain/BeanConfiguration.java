package com.food.ordering.system.restaurant.service.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class BeanConfiguration {

    @Bean
    public RestaurantDomainService restaurantDomainService(){

        return new RestaurantDomainServiceImpl();
    }
}
