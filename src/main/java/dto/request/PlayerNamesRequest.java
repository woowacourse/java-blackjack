package dto.request;

import java.util.Arrays;
import java.util.List;

public record PlayerNamesRequest(List<String> names) {

    private static final String DELIMITER = ",";

    public static PlayerNamesRequest from(String rawInput) {
        requireNonBlank(rawInput);
        return new PlayerNamesRequest(namesFrom(rawInput));
    }

    private static List<String> namesFrom(String rawInput) {
        return Arrays.stream(rawInput.split(DELIMITER))
                .map(String::trim)
                .filter(name -> !name.isEmpty())
                .toList();
    }

    private static void requireNonBlank(String rawInput) {
        if (rawInput == null || rawInput.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 입력값이 비어있습니다.");
        }
    }
}
