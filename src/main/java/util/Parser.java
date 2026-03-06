package util;

import java.util.Arrays;
import java.util.List;

public class Parser {
    private String delimeter;
    public Parser(String delimeter) {
        this.delimeter = delimeter;
    }

    public List<String> parse(String inputNames) {
        return Arrays.stream(inputNames.split(delimeter))
                .map(String::trim)
                .toList();
    }
}
