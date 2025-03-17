package match;

import player.Dealer;
import player.Participant;

import java.util.function.IntUnaryOperator;

public enum MatchResult {
    WIN("승", amount -> amount),
    WIN_WITH_BLACKJACK("승", amount -> (int) (amount * 1.5)),
    LOSE("패", amount -> -amount),
    DRAW("무", amount -> 0);

    private final String title;
    private final IntUnaryOperator betResultCalculator;

    MatchResult(String title, IntUnaryOperator bettingResultCalculator) {
        this.title = title;
        this.betResultCalculator = bettingResultCalculator;
    }

    public int calculateBettingResult(int amount) {
        return betResultCalculator.applyAsInt(amount);
    }

    public static MatchResult calculateParticipantMatchResult(Dealer dealer, Participant participant) {
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

    public String getTitle() {
        return title;
    }
}
