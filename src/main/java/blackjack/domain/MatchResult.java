package blackjack.domain;

import java.util.Arrays;
import java.util.function.IntPredicate;

public enum MatchResult {
    WIN("승", scoreComparison -> scoreComparison > 0),
    LOSE("패", scoreComparison -> scoreComparison < 0),
    DRAW("무", scoreComparison -> scoreComparison == 0);

    private final String display;
    private final IntPredicate scoreMatcher;

    MatchResult(String display, IntPredicate scoreMatcher) {
        this.display = display;
        this.scoreMatcher = scoreMatcher;
    }

    public String getDisplay() {
        return display;
    }

    public boolean matches(Player player, Dealer dealer) {
        if (player.isBust()) {
            return this == LOSE;
        }
        if (dealer.isBust()) {
            return this == WIN;
        }
        return scoreMatcher.test(compareScore(player, dealer));
    }

    public static MatchResult of(Player player, Dealer dealer) {
        return Arrays.stream(values())
                .filter(result -> result.matches(player, dealer))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("매칭되는 결과가 없습니다."));
    }

    private int compareScore(Player player, Dealer dealer) {
        return player.score() - dealer.score();
    }
}