package domain.player;

import java.util.HashSet;
import java.util.List;

public class Names {

    private final List<String> names;

    private Names(List<String> names) {
        this.names = names;
    }

    public static Names from(List<String> names) {
        validateDuplicate(names);
        return new Names(names);
    }

    private static void validateDuplicate(final List<String> names) {
        if (isDuplicate(names)) {
            throw new IllegalArgumentException("중복되지 않은 이름만 입력해주세요");
        }
    }

    private static boolean isDuplicate(final List<String> participants) {
        int uniqueNameCount = new HashSet<>(participants).size();
        return uniqueNameCount < participants.size();
    }

    public List<String> getNames() {
        return List.copyOf(names);
    }
}
