package blackjack.domain;

import java.util.Arrays;
import java.util.function.BiPredicate;

import static blackjack.domain.Round.GAME_OVER_SCORE;

public enum Outcome {
    WIN("승", (dealer, player) -> player > GAME_OVER_SCORE, (dealer, player) -> ((dealer <= GAME_OVER_SCORE) && dealer > player)),
    LOSE("패", (dealer, player) -> dealer > GAME_OVER_SCORE, (dealer, player) -> dealer < player),
    DRAW("무", (dealer, player) -> false, (dealer, player) -> dealer == player);

    private final String name;
    private final BiPredicate<Integer, Integer> busterPredicate;
    private final BiPredicate<Integer, Integer> gamePredicate;

    Outcome(final String name, BiPredicate<Integer, Integer> busterPredicate, final BiPredicate<Integer, Integer> gamePredicate) {
        this.name = name;
        this.busterPredicate = busterPredicate;
        this.gamePredicate = gamePredicate;
    }

    public static Outcome findOutcome(final int dealerScore, final int playerScore) {
        return Arrays.stream(values())
                .filter(o -> o.busterPredicate.or(o.gamePredicate).test(dealerScore, playerScore))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("체크할수 없습!!"));
    }

    public static Outcome getPlayerOutcome(final Outcome outcome) {
        if (outcome == WIN) {
            return LOSE;
        }
        if (outcome == LOSE) {
            return WIN;
        }
        return outcome;
    }

    public boolean isWin(final Outcome outcome) {
        return outcome == WIN;
    }

    public boolean isLose(final Outcome outcome) {
        return outcome == LOSE;
    }

    public boolean isDraw(final Outcome outcome) {
        return outcome == DRAW;
    }

    public String getName() {
        return name;
    }
}
