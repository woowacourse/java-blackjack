package util;

import java.util.List;

public interface Parser<T> {

    List<T> splitToDelimiter(String input, String delimiter);
}
