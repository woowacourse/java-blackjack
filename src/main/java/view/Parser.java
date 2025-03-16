package view;

import java.util.Arrays;
import java.util.List;

public class Parser {

    public static List<String> parserStringToList(final String names) {
        return Arrays.stream(names.split(","))
                .toList()
                .stream()
                .map(String::trim)
                .toList();
    }
}
