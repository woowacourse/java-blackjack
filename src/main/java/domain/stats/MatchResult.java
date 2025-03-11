package domain.stats;

import java.util.List;

public enum MatchResult {
    WIN("승"),
    LOSE("패"),
    DRAW("무"),
    ;

    private final String title;

    MatchResult(String title) {
        this.title = title;
    }

    public static MatchResult compareBySum(int sum1, int sum2) {
        if (sum1 > sum2) {
            return WIN;
        }
        if (sum1 < sum2) {
            return LOSE;
        }
        return DRAW;
    }

    public static MatchResult inverse(MatchResult matchResult) {
        if (matchResult == WIN) {
            return LOSE;
        }
        if (matchResult == LOSE) {
            return WIN;
        }
        return DRAW;
    }

    public static List<MatchResult> sortedValues() {
        return List.of(WIN, LOSE, DRAW);
    }

    public String getTitle() {
        return title;
    }
}
