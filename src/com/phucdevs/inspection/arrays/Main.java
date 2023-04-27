package com.phucdevs.inspection.arrays;

import java.lang.reflect.Array;

public class Main {

    public static void main(String[] args) {

        int[] oneDimensionArray = {1, 2};
        double[][] twoDimensionArray = {{1.5, 2.5}, {3.5, 4.5}};
        inspectArrayObject(twoDimensionArray);
        inspectArrayValues(twoDimensionArray);
    }

    static void inspectArrayValues(Object arrayObject) {

        int lengths = Array.getLength(arrayObject);
        System.out.print("[");

        for (int i = 0; i < lengths; i++) {
            Object element = Array.get(arrayObject, i);
            if (element.getClass().isArray()) {
                inspectArrayValues(element);
            } else {
                System.out.print(element);
            }

            if (i != lengths - 1) {
                System.out.print(" , ");
            }
        }
        System.out.print("]");
    }

    static void inspectArrayObject(Object arrayObject) {

        Class<?> clazz = arrayObject.getClass();
        System.out.println(String.format("Is array : %s", clazz.isArray()));
        Class<?> componentTypes = clazz.getComponentType();
        System.out.println(String.format("This is an array of type: %s",
                componentTypes.getTypeName()));
    }
}
