package domain.participant;

import java.util.List;

public class Names {
    private static final int MAXIMUM_PLAYER_NUMBER = 6;

    private final List<Name> names;

    public Names(final List<String> names) {
        validatePlayerNumbers(names);
        validateIsDuplicate(names);
        this.names = names.stream()
                .map(Name::new)
                .toList();
    }

    private void validatePlayerNumbers(final List<String> names) {
        if (names.isEmpty() || names.size() > MAXIMUM_PLAYER_NUMBER) {
            throw new IllegalArgumentException("[ERROR] 플레이어 인원은 1~6명 입니다.");
        }
    }

    private void validateIsDuplicate(final List<String> names) {
        if (names.stream().distinct().count() != names.size()) {
            throw new IllegalArgumentException("[ERROR] 중복된 이름의 플레이어가 게임에 참여할 수 없습니다.");
        }
    }

    public List<Name> getNames() {
        return names;
    }
}
