package blackjack.domain.bet;

import blackjack.domain.card.BlackjackStatus;
import blackjack.domain.card.Hands;
import blackjack.domain.rule.GameRule;
import java.util.Arrays;
import java.util.function.BiPredicate;

public enum BetStatus {
    LUCKY(1.5,
            (dealerHands, playerHands) -> isStartBlackjack(playerHands) && isNotStartBlackjack(dealerHands)),
    WIN(1,
            (dealerHands, playerHands) -> isNotDead(playerHands)
            && (isDead(dealerHands) || playerHands.isScoreBiggerThan(dealerHands))),
    LOSE(-1,
            (dealerHands, playerHands) -> isDead(playerHands)
            || (isStartBlackjack(dealerHands) && isNotStartBlackjack(playerHands))
            || (isNotDead(dealerHands) && dealerHands.isScoreBiggerThan(playerHands))),
    DRAW(0,
            (dealerHands, playerHands) -> isNotDead(dealerHands) && isNotDead(playerHands)
            && playerHands.isScoreSame(dealerHands));

    private final double leverage;
    private final BiPredicate<Hands, Hands> condition;

    BetStatus(final double leverage, final BiPredicate<Hands, Hands> condition) {
        this.leverage = leverage;
        this.condition = condition;
    }

    public static BetStatus of(final Hands dealerHands, final Hands playerHands) {
        return Arrays.stream(values())
                .filter(winStatus -> winStatus.condition.test(dealerHands, playerHands))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("잘못된 점수입니다."));
    }

    private static boolean isStartBlackjack(final Hands hands) {
        return isBlackjack(hands) && hands.isSizeOf(GameRule.START_CARD_COUNT);
    }

    private static boolean isNotStartBlackjack(final Hands hands) {
        return !isStartBlackjack(hands);
    }
    private static boolean isDead(final Hands hands) {
        return BlackjackStatus.from(hands.calculateScore()) == BlackjackStatus.DEAD;
    }

    private static boolean isNotDead(final Hands hands) {
        return !isDead(hands);
    }

    private static boolean isBlackjack(final Hands hands) {
        return BlackjackStatus.from(hands.calculateScore()) == BlackjackStatus.BLACKJACK;
    }

    public BetRevenue applyLeverage(final BetAmount batAmount) {
        return new BetRevenue(batAmount.multiply(leverage));
    }

}
