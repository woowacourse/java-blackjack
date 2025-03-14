package model.score;

import java.util.EnumMap;

public enum MatchResult {

    WIN,
    LOSE,
    DRAW;

    public static MatchResult fromCompare(int compare) {
        if (compare > 0) {
            return WIN;
        }
        if (compare < 0) {
            return LOSE;
        }
        return DRAW;
    }

    public static EnumMap<MatchResult, Integer> reverseAll(EnumMap<MatchResult, Integer> matchCounts) {
        EnumMap<MatchResult, Integer> reverseMatchCounts = new EnumMap<>(MatchResult.class);
        for (MatchResult matchResult : matchCounts.keySet()) {
            MatchResult reverseMatchResult = reverse(matchResult);
            reverseMatchCounts.put(reverseMatchResult, matchCounts.get(matchResult));
        }
        return reverseMatchCounts;
    }


    private static MatchResult reverse(MatchResult result) {
        if (result == WIN) {
            return LOSE;
        }
        if (result == LOSE) {
            return WIN;
        }
        return result;
    }
}
