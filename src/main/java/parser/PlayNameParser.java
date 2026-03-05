package parser;

import java.util.Arrays;
import java.util.List;

public class PlayNameParser {
    private PlayNameParser() {
    }

    public static List<String> splitNames(String names) {
        return Arrays.asList(names.split(","));
    }
}
