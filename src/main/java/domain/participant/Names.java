package domain.participant;

import java.util.List;
import view.Error;

public class Names {
    private static final int MAXIMUM_PLAYER_NUMBER = 6;

    private final List<Name> names;

    public Names(final List<String> playerNames) {
        List<Name> names = playerNames.stream()
                .map(Name::new)
                .toList();
        validatePlayerNumbers(playerNames);
        validateIsDuplicate(playerNames);
        this.names = names;
    }

    private void validatePlayerNumbers(final List<String> names) {
        if (names.isEmpty() || names.size() > MAXIMUM_PLAYER_NUMBER) {
            throw new IllegalArgumentException(Error.formatMessage("플레이어 인원은 1~6명 입니다."));
        }
    }

    private void validateIsDuplicate(final List<String> names) {
        if (names.stream().distinct().count() != names.size()) {
            throw new IllegalArgumentException(Error.formatMessage("중복된 이름의 플레이어가 게임에 참여할 수 없습니다."));
        }
    }

    public List<Name> getNames() {
        return names;
    }
}
