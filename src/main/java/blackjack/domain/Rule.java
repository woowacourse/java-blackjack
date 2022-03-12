package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public enum Rule {

    INSTANCE;

    public Outcome judgeOutcome(Player player, Dealer dealer) {
        if (!player.isBust() && dealer.isBust() || player.isBlackJack() && !dealer.isBlackJack()) {
            return Outcome.WIN;
        }
        if (player.isBlackJack() && dealer.isBlackJack()) {
            return Outcome.DRAW;
        }
        if (player.isBust() || !player.isBlackJack() && dealer.isBlackJack()) {
            return Outcome.LOSE;
        }
        return judgeOutcomeByScore(player.getScore(), dealer.getScore());
    }

    private Outcome judgeOutcomeByScore(int score, int target) {
        if (score > target) {
            return Outcome.WIN;
        }
        if (score == target) {
            return Outcome.DRAW;
        }
        return Outcome.LOSE;
    }
}
