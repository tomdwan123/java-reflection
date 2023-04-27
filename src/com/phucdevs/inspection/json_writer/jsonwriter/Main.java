package com.phucdevs.inspection.json_writer.jsonwriter;

import com.phucdevs.inspection.json_writer.data.Address;
import com.phucdevs.inspection.json_writer.data.Person;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

class Main {

    public static void main(String[] args) throws IllegalAccessException {

        Address address = new Address("Main Street", (short) 1);
        String json = objectToJson(address, 0);
        //System.out.println(json);

        Person person = new Person("John", true, 29, 100.55f, address);
        System.out.println(objectToJson(person, 0));
    }

    public static String objectToJson(Object instance, int indentSize) throws IllegalAccessException {

        Field[] fields = instance.getClass().getDeclaredFields();
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("{");
        stringBuilder.append("\n");

        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);

            if (field.isSynthetic()) {
                continue;
            }

            stringBuilder.append(indent(indentSize + 1));
            stringBuilder.append(formatStringValue(field.getName()));
            stringBuilder.append(":");

            if (field.getType().isPrimitive()) {
                stringBuilder.append(formatPrimitiveValue(field.get(instance), field.getType()));
            } else if (field.getType().equals(String.class)) {
                stringBuilder.append(formatStringValue(field.get(instance).toString()));
            } else if (field.getType().isArray()) {
                stringBuilder.append(arrayToJson(field.get(instance)));
            }
            else {
                stringBuilder.append(objectToJson(field.get(instance), indentSize + 1));
            }

            if (i != fields.length - 1) {
                stringBuilder.append(",");
            }
            stringBuilder.append("\n");
        }

        stringBuilder.append(indent(indentSize));
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    private static String arrayToJson(Object instance) throws IllegalAccessException {

        StringBuilder stringBuilder = new StringBuilder();
        int lengths = Array.getLength(instance);
        Class<?> componentType = instance.getClass().getComponentType();
        stringBuilder.append("[");

        for (int i = 0; i < lengths; i++) {
            Object element = Array.get(instance, i);
            if (componentType.isPrimitive()) {
                stringBuilder.append(formatPrimitiveValue(element, componentType));
            } else if (componentType.equals(String.class)) {
                stringBuilder.append(formatStringValue(element.toString()));
            }

            if (i != lengths - 1) {
                stringBuilder.append(",");
            }

        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    private static String indent(int indentSize) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i <= indentSize; i++) {
            stringBuilder.append("\t");
        }
        return stringBuilder.toString();
    }

    private static String formatPrimitiveValue(Object instance, Class<?> type) throws IllegalAccessException {

        if (type.equals(boolean.class)
            || type.equals(int.class)
            || type.equals(long.class)
            || type.equals(short.class)) {
            return instance.toString();
        } else if (type.equals(double.class) || type.equals(float.class)) {
            return String.format("%.2f", instance);
        }

        throw new RuntimeException(String.format("Type: %s is unsupported", type.getName()));
    }

    private static String formatStringValue(String value) {
        return String.format("\"%s\"", value);
    }
}
