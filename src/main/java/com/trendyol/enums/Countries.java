package com.trendyol.enums;

public enum Countries {

    UNITED_KINGDOM("United Kingdom", "GB");

    private final String name;
    private final String value;

    Countries(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
