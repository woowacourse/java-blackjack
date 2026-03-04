package util;

import java.util.Arrays;
import java.util.List;

public class InputParser {

    public static List<String> parseNames(String input) {

        input = input.replace(" ", "");
        return Arrays.asList(input.split(","));
    }
}
