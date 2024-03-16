package blackjack.domain.bet;

import blackjack.domain.BlackjackGame;
import blackjack.domain.card.Hands;
import java.util.Arrays;
import java.util.function.BiPredicate;

public enum BetLeverage {
    BLACKJACK(1.5, BetLeverage::isBlackjackWin),
    WIN(1, BetLeverage::isWin),
    LOSE(-1, BetLeverage::isLose),
    PUSH(0, BetLeverage::isPush);

    private final double leverage;
    private final BiPredicate<Hands, Hands> condition;

    BetLeverage(double leverage, BiPredicate<Hands, Hands> condition) {
        this.leverage = leverage;
        this.condition = condition;
    }

    public static BetLeverage of(Hands playerHands, Hands dealerHands) {
        return Arrays.stream(values())
                .filter(betLeverage -> betLeverage.condition.test(playerHands, dealerHands))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("잘못된 점수입니다."));
    }

    private static boolean isBlackjackWin(Hands playerHands, Hands dealerHands) {
        return isBlackjack(playerHands) && isNotBlackjack(dealerHands);
    }

    private static boolean isWin(Hands playerHands, Hands dealerHands) {
        return isNotBust(playerHands) && (isBust(dealerHands) || isScoreBigger(playerHands, dealerHands));
    }

    private static boolean isLose(Hands playerHands, Hands dealerHands) {
        return isBust(playerHands) || (isBlackjack(dealerHands) && isNotBlackjack(playerHands))
                || (isNotBust(dealerHands) && isScoreBigger(dealerHands, playerHands));
    }

    private static boolean isPush(Hands playerHands, Hands dealerHands) {
        return isNotBust(dealerHands) && isNotBust(playerHands) && isScoreSame(playerHands, dealerHands);
    }

    private static boolean isBlackjack(Hands hands) {
        return hands.calculateScore().isBlackjack() && hands.size() == BlackjackGame.START_CARD_COUNT;
    }

    private static boolean isNotBlackjack(Hands hands) {
        return !isBlackjack(hands);
    }

    private static boolean isBust(Hands hands) {
        return hands.calculateScore().isBust();
    }

    private static boolean isNotBust(Hands hands) {
        return !isBust(hands);
    }

    private static boolean isScoreBigger(Hands first, Hands second) {
        return first.calculateScore().compareTo(second.calculateScore()) > 0;
    }

    private static boolean isScoreSame(Hands first, Hands second) {
        return first.calculateScore().equals(second.calculateScore());
    }

    public BetRevenue applyLeverage(BetAmount betAmount) {
        return new BetRevenue(betAmount.multiply(leverage));
    }
}
