package model.score;

import java.util.Arrays;
import java.util.List;

public enum ResultType {

    WIN_LOSE(1, List.of(MatchResult.WIN, MatchResult.LOSE)),
    DRAW(0,List.of(MatchResult.DRAW, MatchResult.DRAW)),
    LOSE_WIN(-1,List.of(MatchResult.LOSE, MatchResult.WIN));

    private final int condition;
    private final List<MatchResult> matches;

    ResultType(int condition, List<MatchResult> matches) {
        this.condition = condition;
        this.matches = matches;
    }

    public static ResultType of(int result) {
        return Arrays.stream(ResultType.values())
                .filter(o -> o.condition == result)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재할 수 없는 결과 입니다"));
    }

    public List<MatchResult> getMatches() {
        return matches;
    }
}
