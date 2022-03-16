package blackjack.constant;

import blackjack.domain.card.Hand;
import java.util.Arrays;
import java.util.function.BiPredicate;

public enum MatchResult {

    BLACKJACK("블랙잭", (player, dealer) -> blackjackCondition(player, dealer), 1.5),
    WIN("승", (player, dealer) -> winCondition(player, dealer), 1),
    LOSE("패", (player, dealer) -> loseCondition(player, dealer), -1),
    DRAW("무", (player, dealer) -> drawCondition(player, dealer), 0);

    private final String name;
    private final BiPredicate<Hand, Hand> condition;
    private final double earningsRate;

    MatchResult(String name,
                BiPredicate<Hand, Hand> condition,
                double earningsRate) {
        this.name = name;
        this.condition = condition;
        this.earningsRate = earningsRate;
    }

    private static boolean blackjackCondition(Hand player, Hand dealer) {
        return player.isBlackjack() && !dealer.isBlackjack();
    }

    private static boolean winCondition(Hand player, Hand dealer) {
        return (!player.isBust() && dealer.isBust()) ||
                (!player.isBust() && !dealer.isBust() && player.getScore() > dealer.getScore());
    }

    private static boolean loseCondition(Hand player, Hand dealer) {
        return player.isBust() ||
                (!player.isBust() && !dealer.isBust() && player.getScore() < dealer.getScore());
    }

    private static boolean drawCondition(Hand player, Hand dealer) {
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

    public int getEarnings(int bettingMoney) {
        return (int) (earningsRate * bettingMoney);
    }

    public String getName() {
        return name;
    }
}
