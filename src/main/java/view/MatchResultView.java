package view;

import domain.MatchResult;
import java.util.Arrays;

public enum MatchResultView {
    WIN(MatchResult.WIN, "승"),
    DRAW(MatchResult.DRAW, "무"),
    LOSE(MatchResult.LOSE, "패");

    private final MatchResult matchResult;
    private final String displayName;

    MatchResultView(MatchResult matchResult, String displayName) {
        this.matchResult = matchResult;
        this.displayName = displayName;
    }

    public static String from(MatchResult matchResult) {
        return Arrays.stream(values())
                .filter(view -> view.matchResult == matchResult)
                .findFirst()
                .map(view -> view.displayName)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 승패 결과입니다."));
    }
}
