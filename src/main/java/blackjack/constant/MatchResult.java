package blackjack.constant;

import blackjack.domain.card.Hand;
import java.util.Arrays;
import java.util.function.BiPredicate;

public enum MatchResult {

    BLACKJACK("블랙잭", (player, dealer) -> blackjackCondition(player, dealer)),
    WIN("승", (player, dealer) -> winCondition(player, dealer)),
    LOSE("패", (player, dealer) -> loseCondition(player, dealer)),
    DRAW("무", (player, dealer) -> drawCondition(player, dealer));

    private final String name;

    private final BiPredicate<Hand, Hand> condition;

    MatchResult(String name, BiPredicate<Hand, Hand> condition) {
        this.name = name;
        this.condition = condition;
    }

    private static boolean blackjackCondition(Hand player, Hand dealer) {
        return player.isBlackjack() && !dealer.isBlackjack();
    }

    private static boolean winCondition(Hand player, Hand dealer) {
        return (!player.isBust() && dealer.isBust()) ||
                (!player.isBust() && !dealer.isBust() && player.getScore() > dealer.getScore());
    }

    private static boolean loseCondition(Hand player, Hand dealer) {
        return player.isBust() && !dealer.isBust() ||
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

    public String getName() {
        return name;
    }
}
