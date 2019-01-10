package com.github.frunoman.utils;


public enum Categories {
    SMARTPHONES_AND_TV("Смартфоны, ТВ и электроника"),
    PRODUCTS_FOR_HOME("Товары для дома");

    private final String category;
    Categories(String category){
        this.category=category;
    }

    @Override
    public String toString() {
        return this.category;
    }
}
