package blackjack.domain;

import blackjack.domain.vo.BettingMoney;
import java.util.Arrays;
import java.util.function.BiPredicate;

public enum PlayerOutcome {
    WIN((player, dealer) -> player > dealer && player <= 21 || dealer > 21 && player <= 21),
    LOSE((player, dealer) -> player > 21 || dealer <= 21 && player < dealer),
    DRAW((player, dealer) -> player == 21 && dealer == 21);

    private static final int LOSE_RATIO = -1;

    private BiPredicate<Integer, Integer> matcher;

    PlayerOutcome(BiPredicate<Integer, Integer> matcher) {
        this.matcher = matcher;
    }

    public static PlayerOutcome match(int playerTotal, int dealerTotal) {
        return Arrays.stream(PlayerOutcome.values())
            .filter(outcome -> outcome.matcher.test(playerTotal, dealerTotal))
            .findFirst()
            .orElse(LOSE);
    }

    public int betting(BettingMoney bettingMoney) {
        if (this == LOSE) {
            return bettingMoney.getAmount() * LOSE_RATIO;
        }
        return bettingMoney.getAmount();
    }
}
