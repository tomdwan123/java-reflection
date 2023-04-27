package com.phucdevs.inspection;

import java.lang.reflect.Field;

public class FieldsBasic {

    public static void main(String[] args) throws IllegalAccessException {

        //printDeclaredFieldsInfo(Movie.class);
        //printDeclaredFieldsInfo(Movie.MovieStats.class);

        Movie movie = new Movie("John", 2001, true, Category.ADVENTURE, 12.99);
        printDeclaredFieldsInfo2(movie.getClass(), movie);

    }

    static void printDeclaredFieldsInfo(Class<?> clazz) {

        for (Field field : clazz.getDeclaredFields()) {
            System.out.println(
                    String.format("Field name : %s type : %s",
                            field.getName(),
                            field.getType().getName()));

            System.out.println(String.format("Is synthetic field : %s", field.isSynthetic()));

            System.out.println();
        }
    }

    static <T> void printDeclaredFieldsInfo2(Class<? extends T> clazz, T instance) throws IllegalAccessException {

        for (Field field : clazz.getDeclaredFields()) {
            System.out.println(
                    String.format("Field name : %s type : %s",
                            field.getName(),
                            field.getType().getName()));

            System.out.println(String.format("Is synthetic field : %s", field.isSynthetic()));
            System.out.println(String.format("Field value is $%s", field.get(instance)));

            System.out.println();
        }
    }

    static class Movie extends Product {

        public static final double MINIMUM_PRICE = 1-.99;

        private boolean isReleases;
        private Category category;
        private double actualPrice;

        public Movie(String name, int year, boolean isReleases, Category category, double actualPrice) {
            super(name, year);
            this.isReleases = isReleases;
            this.category = category;
            this.actualPrice = Math.max(actualPrice, MINIMUM_PRICE);
        }

        // Nested class
        public class MovieStats {

            private double timeWatched;

            public MovieStats(double timeWatched) {
                this.timeWatched = timeWatched;
            }

            public double getRevenue() {
                return timeWatched * actualPrice;
            }
        }
    }

    enum Category {
        ADVENTURE,
        ACTION,
        COMEDY
    }

    // Supper class
    static class Product {

        protected String name;
        protected int year;
        protected double actualPrice;

        public Product(String name, int year) {
            this.name = name;
            this.year = year;
        }
    }
}
