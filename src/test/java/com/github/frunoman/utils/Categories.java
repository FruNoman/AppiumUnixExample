package com.github.frunoman.utils;


import java.util.Random;

public enum Categories {
    NOUTBOOKS_AND_COMPUTERS("Ноутбуки и компьютеры"),
    SMARTPHONES_AND_TV("Смартфоны, ТВ и электроника"),
    PRODUCTS_FOR_HOME("Товары для дома"),
    APPLIENCES("Бытовая техника");




    private final String category;
    Categories(String category){
        this.category=category;
    }

    public static Categories getRandomCategory(){
        return Categories.values()[new Random().nextInt(Categories.values().length)];
    }

    @Override
    public String toString() {
        return this.category;
    }
}
