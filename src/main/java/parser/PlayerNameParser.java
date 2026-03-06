package parser;

import java.util.Arrays;
import java.util.List;

public class PlayerNameParser {
    private PlayerNameParser() {
    }

    public static List<String> splitNames(String names) {
        return Arrays.asList(names.replace(" ", "").split(","));
    }
}
