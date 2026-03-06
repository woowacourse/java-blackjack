package util;

import java.util.Arrays;
import java.util.List;

public class StringParser implements Parser<String> {

    @Override
    public List<String> splitToDelimiter(String input, String delimiter) {
        return Arrays.stream(input.split(delimiter))
                .toList();
    }
}
