package domain.name;

import java.util.HashSet;
import java.util.List;

public record Names(List<Name> playerNames) {
    private static final int MIN_SIZE = 1;
    private static final int MAX_SIZE = 8;

    public Names {
        validateNotDuplicate(playerNames);
        validateSize(playerNames);
    }

    private void validateNotDuplicate(final List<Name> names) {
        int nonDuplicatedSize = new HashSet<>(names).size();
        if (nonDuplicatedSize != names.size()) {
            throw new IllegalArgumentException("중복된 이름을 가진 플레이어는 존재할 수 없습니다.");
        }
    }

    private void validateSize(final List<Name> playerNames) {
        if (playerNames.isEmpty() || playerNames.size() > MAX_SIZE) {
            throw new IllegalArgumentException("플레이어의 수는 최소 %d명 최대 %d명입니다 : 현재 %d명"
                    .formatted(MIN_SIZE, MAX_SIZE, playerNames.size()));
        }
    }
}
