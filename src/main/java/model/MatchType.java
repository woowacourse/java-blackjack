package model;

import java.util.Arrays;

public enum MatchType {
    WIN(1),
    LOSE(-1),
    DRAW(0);

    private final int condition;

    MatchType(int condition) {
        this.condition = condition;
    }

    public static MatchType of(int result) {
        return Arrays.stream(MatchType.values())
                .filter(o -> o.condition == result)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재할 수 없는 결과 입니다"));
    }
}
