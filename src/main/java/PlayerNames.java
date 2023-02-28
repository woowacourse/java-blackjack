import java.util.List;
import java.util.stream.Collectors;

public class PlayerNames {
    private final List<PlayerName> names;

    private PlayerNames(List<PlayerName> names) {
        validate(names);
        this.names = names;
    }

    public static PlayerNames from(List<String> names) {
        List<PlayerName> playerNames = names.stream()
                .map(PlayerName::new)
                .collect(Collectors.toList());

        return new PlayerNames(playerNames);
    }

    private void validate(List<PlayerName> names) {
        validateDuplication(names);
    }

    private void validateDuplication(List<PlayerName> names) {
        long distinctionSize = names.stream()
                .map(PlayerName::getName)
                .distinct()
                .count();

        if (names.size() != distinctionSize) {
            throw new IllegalArgumentException("중복된 이름을 허용할 수 없습니다.");
        }
    }

}
