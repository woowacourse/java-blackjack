package blackjack.domain.betting;

import blackjack.domain.card.Hands;
import blackjack.domain.rule.GameRule;
import java.util.Arrays;
import java.util.function.BiPredicate;

public enum BettingStatus {
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

    BettingStatus(final double leverage, final BiPredicate<Hands, Hands> condition) {
        this.leverage = leverage;
        this.condition = condition;
    }

    public static BettingStatus of(final Hands dealerHands, final Hands playerHands) {
        return Arrays.stream(values())
                .filter(winStatus -> winStatus.condition.test(dealerHands, playerHands))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("잘못된 점수입니다."));
    }

    public BetRevenue applyLeverage(final BetAmount batAmount) {
        return new BetRevenue(batAmount.multiply(leverage));
    }
}
