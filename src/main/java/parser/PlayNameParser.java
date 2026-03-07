package parser;

import java.util.Arrays;
import java.util.List;

public class PlayNameParser {

    private static final String NAME_SEPARATOR = ",";
    private static final String WHITESPACE = " ";

    private PlayNameParser() {
    }

    public static List<String> splitNames(String names) {
        return Arrays.asList(
                names.replace(WHITESPACE, "").split(NAME_SEPARATOR)
        );
    }
}
