package blackjack.domain.gamer;

import java.util.HashSet;
import java.util.List;
import java.util.function.Consumer;

public class Players {
    private static final String NAMES_DUPLICATE_ERROR = "플레이어 이름은 중복될 수 없습니다.";
    private final List<Player> values;

    private Players(List<Player> values) {
        this.values = values;
    }

    public static Players from(List<String> names) {
        validateDuplicate(names);
        return new Players(names.stream().map(Player::new).toList());
    }

    private static void validateDuplicate(List<String> names) {
        int distinctCount = new HashSet<>(names).size();
        if (names.size() != distinctCount) {
            throw new IllegalArgumentException(NAMES_DUPLICATE_ERROR);
        }
    }

    public List<String> getNames() {
        return values.stream().map(Player::getName).toList();
    }

    public void forEach(Consumer<? super Player> action) {
        values.forEach(action);
    }
}
