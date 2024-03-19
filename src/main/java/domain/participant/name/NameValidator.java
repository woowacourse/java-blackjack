package domain.participant.name;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class NameValidator {
    private static final int MIN_NAME_LENGTH = 2;
    private static final int MAX_NAME_LENGTH = 5;
    private static final Pattern INVALID_NAME_PATTERN = Pattern.compile("\\P{L}");
    private static final int MIN_NAMES_COUNT = 1;
    private static final int MAX_NAMES_COUNT = 6;

    private NameValidator() {
    }

    public static void validateName(String name) {
        validateLength(name);
        validateContainsSpace(name);
        validateNamePattern(name);
    }

    private static void validateLength(String name) {
        int length = name.length();

        if (length < MIN_NAME_LENGTH || MAX_NAME_LENGTH < length) {
            throw new IllegalArgumentException(
                    String.format("rejected value : %s - 이름은 %d ~ %d 글자여야 합니다", name, MIN_NAME_LENGTH, MAX_NAME_LENGTH)
            );
        }
    }

    private static void validateContainsSpace(String name) {
        if (name.contains(" ")) {
            throw new IllegalArgumentException(
                    String.format("rejected value : %s - 이름에 공백이 포함되어 있습니다.", name)
            );
        }
    }

    private static void validateNamePattern(String name) {
        if (INVALID_NAME_PATTERN.matcher(name).find()) {
            throw new IllegalArgumentException(
                    String.format("rejected value : %s - 이름에 특수문자와 숫자는 허용하지 않습니다.", name)
            );
        }
    }

    public static void validateNames(List<String> names) {
        validateNameCount(names);
        validateDuplicatedName(names);
    }

    private static void validateNameCount(List<String> names) {
        int nameCount = names.size();
        if (nameCount < MIN_NAMES_COUNT || MAX_NAMES_COUNT < nameCount) {
            throw new IllegalArgumentException(
                    String.format("이름 개수는 %d ~ %d개여야 합니다.", MIN_NAMES_COUNT, MAX_NAMES_COUNT)
            );
        }
    }

    private static void validateDuplicatedName(List<String> names) {
        Map<String, Long> nameCounts = getPlayerNameCounts(names);
        Optional<String> duplicatedName = findDuplicatedName(nameCounts);
        if (duplicatedName.isPresent()) {
            throw new IllegalArgumentException(
                    String.format("rejected value: %s - 중복된 이름이 존재합니다.", duplicatedName.get())
            );
        }
    }

    private static Map<String, Long> getPlayerNameCounts(List<String> names) {
        return names.stream()
                .collect(Collectors.groupingBy(name -> name, Collectors.counting()));
    }

    private static Optional<String> findDuplicatedName(Map<String, Long> nameCounts) {
        return nameCounts.entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Entry::getKey)
                .findFirst();
    }

}
