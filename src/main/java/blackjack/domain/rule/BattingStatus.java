package blackjack.domain.rule;

import blackjack.domain.card.Hands;
import java.util.Arrays;
import java.util.function.BiPredicate;

public enum BattingStatus {
    LUCKY(1.5,
            (dealerHands, playerHands) -> playerHands.isBlackjackAndSizeIs(GameRule.START_CARD_COUNT)
            && (dealerHands.isNotBlackjack() || (dealerHands.isBlackjackAndSizeIsNot(GameRule.START_CARD_COUNT)))),
    WIN(1,
            (dealerHands, playerHands) -> playerHands.isNotDead()
            && (dealerHands.isDead() || playerHands.isScoreBiggerThan(dealerHands))),
    LOSE(-1,
            (dealerHands, playerHands) -> playerHands.isDead()
            || (dealerHands.isBlackjackAndSizeIs(GameRule.START_CARD_COUNT) && playerHands.isNotSize(GameRule.START_CARD_COUNT))
            || (dealerHands.isNotDead() && dealerHands.isScoreBiggerThan(playerHands))),
    DRAW(0,
            (dealerHands, playerHands) -> dealerHands.isNotDead() && playerHands.isNotDead()
            && playerHands.isScoreSame(dealerHands));

    private final double leverage;
    private final BiPredicate<Hands, Hands> condition;

    BattingStatus(final double leverage, final BiPredicate<Hands, Hands> condition) {
        this.leverage = leverage;
        this.condition = condition;
    }

    public static BattingStatus of(final Hands dealerHands, final Hands playerHands) {
        return Arrays.stream(values())
                .filter(winStatus -> winStatus.condition.test(dealerHands, playerHands))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("잘못된 점수입니다."));
    }

    public BattingStatus opposite() {
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return DRAW;
    }
}
