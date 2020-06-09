package com.chen.app.test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class T1 {
    public static void main(String[] args) {
        String[] a = {"1", "2", "3","5","9","4","3"};
        List<String> mCollect = Arrays.asList(a).stream().sorted((a1, b2) -> Integer.parseInt(a1) - Integer.parseInt(b2))
                .collect(Collectors.toList());
        mCollect.forEach(b -> System.out.println(b));
    }
}
