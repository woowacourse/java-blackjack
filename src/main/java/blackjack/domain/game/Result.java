package blackjack.domain.game;

import blackjack.domain.card.Cards;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum Result {
    BLACK_JACK_WIN(1.5),
    WIN(1),
    LOSE(-1),
    DRAW(0);

    private final double prizeMultiplier;

    Result(double prizeMultiplier) {
        this.prizeMultiplier = prizeMultiplier;
    }

    public static Result of(Cards userCard, Cards dealerCard) {
        if (isBlackJackWin(userCard, dealerCard)) {
            return BLACK_JACK_WIN;
        }
        if (isWin(userCard, dealerCard)) {
            return WIN;
        }
        if (isLose(userCard, dealerCard)) {
            return LOSE;
        }
        if (isDraw(userCard, dealerCard)) {
            return DRAW;
        }
        throw new IllegalArgumentException("해당 Cards로 결과를 만들 수 없습니다.");
    }

    private static boolean isBlackJackWin(final Cards userCard, final Cards dealerCard) {
        return userCard.isBlackJack() && !dealerCard.isBlackJack();
    }

    private static boolean isWin(final Cards userCard, final Cards dealerCard) {
        final boolean checkBlackJack = !userCard.isBlackJack() && !dealerCard.isBlackJack();
        final boolean winByValue = userCard.getScore().getValue() > dealerCard.getScore().getValue();
        return checkBlackJack && winByValue;
    }

    private static boolean isLose(final Cards userCard, final Cards dealerCard) {
        final boolean loseByBust = userCard.isBust();
        final boolean loseByBlackJack = !userCard.isBlackJack() && dealerCard.isBlackJack();
        final boolean loseByValue = userCard.getScore().getValue() < dealerCard.getScore().getValue();
        return loseByBust || loseByBlackJack || loseByValue;
    }

    private static boolean isDraw(final Cards userCard, final Cards dealerCard) {
        return !userCard.isBust() && userCard.getScore().getValue() == dealerCard.getScore().getValue();
    }

    public int getProfit(final int stake) {
        return (int) (this.prizeMultiplier * stake);
    }
}
