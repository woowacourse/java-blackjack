package blackjack.domain;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum PlayerOutcome {
    WIN("승", (player, dealer) -> player > dealer && player <= 21 || dealer > 21 && player <= 21),
    LOSE("패", (player, dealer) -> player > 21 || dealer <= 21 && player < dealer),
    DRAW("무", (player, dealer) -> player == dealer && player <= 21);

    private String value;
    private BiPredicate<Integer, Integer> matcher;

    PlayerOutcome(String value, BiPredicate<Integer, Integer> matcher) {
        this.value = value;
        this.matcher = matcher;
    }

    public static PlayerOutcome match(int playerTotal, int dealerTotal) {
        return Arrays.stream(PlayerOutcome.values())
            .filter(outcome -> outcome.matcher.test(playerTotal, dealerTotal))
            .findFirst()
            .orElse(LOSE);
    }

    public String getValue() {
        return this.value;
    }
}
