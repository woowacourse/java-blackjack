package blackjack.constant;

import blackjack.domain.card.Hand;
import java.util.Arrays;
import java.util.function.BiPredicate;

public enum MatchResult {

    WIN("승", (self, other) -> winCondition(self, other)),
    LOSE("패", (self, other) -> loseCondition(self, other)),
    DRAW("무", (self, other) -> drawCondition(self, other));

    private final String name;
    private final BiPredicate<Hand, Hand> condition;

    MatchResult(String name, BiPredicate<Hand, Hand> condition) {
        this.name = name;
        this.condition = condition;
    }

    private static boolean winCondition(Hand self, Hand other) {
        return !self.isBust() && other.isBust() ||
                (!self.isBust() && !other.isBust() && self.getScore() > other.getScore());
    }

    private static boolean loseCondition(Hand self, Hand other) {
        return WIN.condition.test(other, self);
    }

    private static boolean drawCondition(Hand self, Hand other) {
        return self.isBust() && other.isBust() || self.getScore() == other.getScore();
    }

    public static MatchResult get(Hand player, Hand dealer) {
        return Arrays.stream(values())
                .filter(matchResult -> matchResult.condition.test(player, dealer))
                .findFirst()
                .orElse(DRAW);
    }

    public MatchResult reverse() {
        if (this == WIN) {
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
