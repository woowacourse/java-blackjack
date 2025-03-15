package score;

import java.util.Arrays;
import java.util.List;

public enum MatchResultCaseType {

    WIN_LOSE(1, List.of(MatchResultType.WIN, MatchResultType.LOSE)),
    DRAW(0, List.of(MatchResultType.DRAW, MatchResultType.DRAW)),
    LOSE_WIN(-1, List.of(MatchResultType.LOSE, MatchResultType.WIN));

    private final int condition;
    private final List<MatchResultType> matches;

    MatchResultCaseType(int condition, List<MatchResultType> matches) {
        this.condition = condition;
        this.matches = matches;
    }

    public static MatchResultCaseType of(int result) {
        return Arrays.stream(MatchResultCaseType.values())
                .filter(o -> o.condition == result)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재할 수 없는 결과 입니다"));
    }

    public List<MatchResultType> getMatches() {
        return matches;
    }
}
