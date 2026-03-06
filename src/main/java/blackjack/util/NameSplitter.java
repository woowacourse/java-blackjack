package blackjack.util;

import java.util.Arrays;
import java.util.List;

public class NameSplitter {

    public static final String DELIMITER = ",";

    public static List<String> split(String names) {
        return Arrays.stream(names.split(DELIMITER))
                .map(s -> s.trim())
                .toList();
    }
}
