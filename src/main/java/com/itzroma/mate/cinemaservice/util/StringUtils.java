package com.itzroma.mate.cinemaservice.util;

public class StringUtils {
    private StringUtils() {
    }

    public static String camelCaseToRegularText(String string) {
        return string.replaceAll("([a-z])([A-Z]+)", "$1 $2").toLowerCase();
    }

    public static String pluralOf(String string) {
        if (string.endsWith("s")
                || string.endsWith("sh")
                || string.endsWith("ch")
                || string.endsWith("x")
                || string.endsWith("z")) {
            return string + "es";
        }
        if (string.endsWith("y")) {
            return string.substring(0, string.length() - 1) + "ies";
        }
        return string + "s";
    }
}
