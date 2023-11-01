package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Scanner;

@Getter
@Setter
@AllArgsConstructor
public class Container {
    @Getter
    private static Scanner sc;


    public static void init() {
        sc = new Scanner(System.in);
    }

    public static void close() {
        sc.close();
    }

}
