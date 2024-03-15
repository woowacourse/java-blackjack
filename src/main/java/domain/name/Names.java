package domain.name;

import java.util.HashSet;
import java.util.List;

public record Names(List<Name> playerNames) {
    public Names {
        validateNotDuplicate(playerNames);
    }

    private void validateNotDuplicate(final List<Name> names) {
        int nonDuplicatedSize = new HashSet<>(names).size();
        if (nonDuplicatedSize != names.size()) {
            throw new IllegalArgumentException("중복된 이름을 가진 플레이어는 존재할 수 없습니다.");
        }
    }
}
