package result;

import player.Player;

public enum MatchResult {
    WIN("승"),
    LOSE("패"),
    DRAW("무"),
    ;

    private final String title;

    MatchResult(String title) {
        this.title = title;
    }

    private static MatchResult compareBySum(int sum1, int sum2) {
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

    public static MatchResult calculateParticipantMatchResult(Player dealer, Player participant) {
        if (participant.isBust() && dealer.isBust() || participant.isBust()) {
            return MatchResult.LOSE;
        }
        if (dealer.isBust()) {
            return MatchResult.WIN;
        }
        return compareBySum(participant.computeOptimalSum(),
                dealer.computeOptimalSum());
    }

    public String getTitle() {
        return title;
    }
}
