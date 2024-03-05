package blackjack.domain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Players {
    private static final String NAMES_EMPTY_ERROR = "공백이 아닌 플레이어를 입력해 주세요.";
    private static final String NAMES_DUPLICATE_ERROR = "플레이어 이름은 중복될 수 없습니다.";
    private final List<Player> values;

    private Players(List<Player> values) {
        this.values = values;
    }

    public static Players from(String names) {
        validate(names);
        return new Players(parseNames(names).stream().map(Player::new).toList());
    }

    private static void validate(String names) {
        validateDuplicate(names);
        validateEmpty(names);
    }

    private static void validateDuplicate(String names) {
        List<String> splitNames = parseNames(names);
        int distinctCount = new HashSet<>(splitNames).size();
        if (splitNames.size() != distinctCount) {
            throw new IllegalArgumentException(NAMES_DUPLICATE_ERROR);
        }
    }

    private static void validateEmpty(String names) {
        if (names.isBlank()) {
            throw new IllegalArgumentException(NAMES_EMPTY_ERROR);
        }
    }

    private static List<String> parseNames(String names) {
        return Arrays.stream(names.split(",")).toList();
    }

    public List<String> getNames() {
        return values.stream().map(Player::getName).toList();
    }
}
