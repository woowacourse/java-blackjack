package result;

import player.Player;

public enum MatchResult {
    WIN("승"),
    WIN_WITH_BLACKJACK("승"),
    LOSE("패"),
    DRAW("무"),
    ;

    private final String title;

    MatchResult(String title) {
        this.title = title;
    }

    public static MatchResult calculateParticipantMatchResult(Player dealer, Player participant) {
        if (participant.isBlackjack() && dealer.isBlackjack()) {
            return DRAW;
        }
        if (participant.isBust() || (participant.isBust() && dealer.isBust())) {
            return LOSE;
        }
        if (dealer.isBust()) {
            return WIN;
        }
        if (participant.isBlackjack()) {
            return WIN_WITH_BLACKJACK;
        }
        return compareBySum(participant.computeOptimalSum(), dealer.computeOptimalSum());
    }

    public static MatchResult inverse(MatchResult matchResult) {
        if (matchResult == DRAW) {
            return DRAW;
        }
        if (matchResult == LOSE) {
            return WIN;
        }
        return LOSE;
    }

    public String getTitle() {
        return title;
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
}
