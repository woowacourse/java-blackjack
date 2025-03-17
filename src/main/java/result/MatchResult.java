package result;

import player.Player;

public enum MatchResult {
    WIN,
    WIN_WITH_BLACKJACK,
    LOSE,
    DRAW,
    ;

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
