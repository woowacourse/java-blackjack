package domain;

import java.util.List;

public class Names {
    public Names(final List<String> names) {
        if (names.size() < 1 || names.size() > 7) {
            throw new IllegalArgumentException("전체 플레이어 수는 1명 이상 7명 이하여야 합니다!");
        }
    }
}
