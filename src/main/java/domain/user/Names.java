package domain.user;

import java.util.ArrayList;
import java.util.List;

public class Names {
    private static final int MAX_PLAYER_SIZE = 5;

    private final List<Name> names = new ArrayList<>();

    public Names(List<String> inputNames) {
        validateDuplicatedName(inputNames);
        validateNamesSize(inputNames);
        inputNames.forEach(name -> names.add(new Name(name)));
    }

    public List<Name> getNames() {
        return new ArrayList<>(names);
    }

    private void validateDuplicatedName(List<String> names) {
        if (names.size() != names.stream().distinct().count()) {
            throw new IllegalArgumentException("[ERROR] 플레이어 이름은 중복될 수 없습니다.");
        }
    }

    private void validateNamesSize(List<String> names) {
        if (names.size() > MAX_PLAYER_SIZE) {
            throw new IllegalArgumentException("[ERROR] 플레이어는 최대 5명입니다.");
        }
    }
}
