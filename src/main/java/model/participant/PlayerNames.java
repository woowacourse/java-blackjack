package model.participant;

import java.util.List;
import java.util.Set;

public final class PlayerNames {
    private final List<String> values;

    private PlayerNames(List<String> values) {
        this.values = List.copyOf(values);
    }

    public static PlayerNames from(List<String> names) {
        List<String> copied = List.copyOf(names);
        validateDuplicateNames(copied);

        return new PlayerNames(copied);
    }

    private static void validateDuplicateNames(List<String> names) {
        if (Set.copyOf(names).size() != names.size()) {
            throw new IllegalArgumentException("플레이어의 이름은 서로 중복될 수 없습니다. 현재 이름 목록: " + names);
        }
    }

    public List<String> asList() {
        return List.copyOf(values);
    }
}
