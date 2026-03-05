package util;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class InputParser {

    public List<String> parseName(String input) {
        StringTokenizer stringTokenizer = new StringTokenizer(input.strip(), ",");
        List<String> names = new ArrayList<>();

        validateEmptyInput(names);

        while(stringTokenizer.hasMoreTokens()){
            names.add(stringTokenizer.nextToken());
        }

        return names;
    }

    private static void validateEmptyInput(List<String> names) {
        if (names.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }
}
