package com.food.ordering.system.domain.valueobject;

import java.util.UUID;

/**
 * @author Mouhsine Lahridi
 */
public class ResturantId extends BaseId<UUID>{
    public ResturantId(UUID value) {
        super(value);
    }
}
