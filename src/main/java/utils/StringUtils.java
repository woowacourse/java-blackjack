package utils;

import java.util.Arrays;
import java.util.List;

public class StringUtils {
    public static List<String> parseWithDelimeter(String input, String delimiter) {
        return Arrays.asList(input.split(delimiter));
    }
}
