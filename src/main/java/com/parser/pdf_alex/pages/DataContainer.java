package com.parser.pdf_alex.pages;

public class DataContainer {
    private String name;
    private String allergen;
    private String type;
    private String proteins;
    private String value;

    public DataContainer(String name, String allergen, String type, String proteins, String value) {
        this.name = name;
        this.allergen = allergen;
        this.type = type;
        this.proteins = proteins;
        this.value = value;
    }


    public String getName() {
        return name;
    }

    public String getAllergen() {
        return allergen;
    }

    public String getType() {
        return type;
    }

    public String getProteins() {
        return proteins;
    }

    public String getValue() {
        return value;
    }
}
