package blackjack.constant;

import blackjack.domain.card.Hand;
import java.util.Arrays;
import java.util.function.BiPredicate;

public enum MatchResult {

    BLACKJACK(1.5, MatchResult::playerBlackJackWinCondition),
    WIN(1, MatchResult::playerWinCondition),
    LOSE(-1, MatchResult::playerLoseCondition),
    DRAW(0, MatchResult::playerDrawCondition);

    private final double returnRate;
    private final BiPredicate<Hand, Hand> condition;

    MatchResult(double returnRate, BiPredicate<Hand, Hand> condition) {
        this.condition = condition;
        this.returnRate = returnRate;
    }

    private static boolean playerBlackJackWinCondition(Hand player, Hand dealer) {
        return player.isBlackjack() && !dealer.isBlackjack();
    }

    private static boolean playerWinCondition(Hand player, Hand dealer) {
        return (!player.isBust() && dealer.isBust()) ||
                (!player.isBust() && !dealer.isBust() && player.getScore() > dealer.getScore());
    }

    private static boolean playerLoseCondition(Hand player, Hand dealer) {
        return player.isBust() ||
                (!player.isBust() && !dealer.isBust() && player.getScore() < dealer.getScore());
    }

    private static boolean playerDrawCondition(Hand player, Hand dealer) {
        return player.isBust() && dealer.isBust() ||
                player.getScore() == dealer.getScore() ||
                player.isBlackjack() && dealer.isBlackjack();
    }

    public static MatchResult get(Hand player, Hand dealer) {
        return Arrays.stream(values())
                .filter(matchResult -> matchResult.condition.test(player, dealer))
                .findFirst()
                .orElse(DRAW);
    }

    public MatchResult reverse() {
        if (this == WIN || this == BLACKJACK) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return DRAW;
    }

    public int getReturn(int bettingMoney) {
        return Double.valueOf(returnRate * bettingMoney)
                .intValue();
    }
}
