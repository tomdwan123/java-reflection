package com.phucdevs.inspection.json_writer.data;

public class Person {

    private final String name;
    private final boolean employed;
    private final int age;
    private final float salary;
    private final Address address;

    public Person(String name, boolean employed, int age, float salary, Address address) {
        this.name = name;
        this.employed = employed;
        this.age = age;
        this.salary = salary;
        this.address = address;
    }
}
