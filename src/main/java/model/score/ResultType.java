package model.score;

import java.util.Arrays;
import java.util.List;

public enum ResultType {

    WIN_LOSE(1, List.of(MatchType.WIN, MatchType.LOSE)),
    DRAW(0, List.of(MatchType.DRAW, MatchType.DRAW)),
    LOSE_WIN(-1, List.of(MatchType.LOSE, MatchType.WIN));

    private final int condition;
    private final List<MatchType> matches;

    ResultType(int condition, List<MatchType> matches) {
        this.condition = condition;
        this.matches = matches;
    }

    public static ResultType of(int result) {
        return Arrays.stream(ResultType.values())
                .filter(o -> o.condition == result)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재할 수 없는 결과 입니다"));
    }

    public List<MatchType> getMatches() {
        return matches;
    }
}
