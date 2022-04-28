package com.trendyol.enums;

import java.util.Arrays;
import java.util.List;

public enum Categories {
    NEW_IN("New in", Arrays.asList("New Arrivals", "Dresses"));

    private final String name;
    private final List<String> subCategories;

    Categories(String name, List<String> subCategories) {
        this.name = name;
        this.subCategories = subCategories;
    }

    public List<String> getSubCategories() {
        return subCategories;
    }

    public String getName() {
        return name;
    }
}
