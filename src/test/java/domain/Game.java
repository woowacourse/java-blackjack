package domain;

import java.util.List;
import java.util.Set;

public class Game {

    public Game(List<String> names) {
        validateDuplicatedName(names);
    }

    private void validateDuplicatedName(List<String> names) {
        Set<String> distinctNames = Set.copyOf(names);
        if (distinctNames.size() != names.size()) {
            throw new IllegalArgumentException("[ERROR] 플레이어 이름은 중복될 수 없습니다.");
        }
    }
}