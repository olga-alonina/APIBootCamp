package com.cydeo.tests.compare2jsonfiles;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class SetOfServers {
    @Test
    public void main() {
        Map<Integer, Integer> input = new HashMap<>();
        input.put(1, 5);
        input.put(2, 4);
        input.put(3, 5);
        input.put(4, 6);
        input.put(5, 2);
        input.put(6, 4);
        System.out.println("getKeysByValue(input, 5) = " + getKeysByValue(input, 5));
        System.out.println("getKeysByValue(input, 4) = " + getKeysByValue(input, 4));
        System.out.println("getKeysByValue(input, 6) = " + getKeysByValue(input, 6));
        System.out.println("getKeysByValue(input, 2) = " + getKeysByValue(input, 2));
    }

    public static <T, E> Set<T> getKeysByValue(Map<T, E> map, E value) {
        return map.entrySet()
                .stream()
                .filter(entry -> Objects.equals(entry.getValue(), value))
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }
}