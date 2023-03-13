package blackjack.domain.game;

import blackjack.domain.card.Cards;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum Result {
    BLACK_JACK_WIN(1.5,
            (userCard, dealerCard) -> userCard.isBlackJack() &&
                    !dealerCard.isBlackJack()
    ),
    WIN(1,
            (userCard, dealerCard) -> !userCard.isBlackJack() &&
                    userCard.getScore().getValue() > dealerCard.getScore().getValue()
    ),
    LOSE(-1, (userCard, dealerCard) -> !userCard.isBlackJack() &&
            userCard.getScore().getValue() < dealerCard.getScore().getValue()),
    DRAW(0, (userCard, dealerCard) -> !userCard.isBust() &&
            userCard.getScore().getValue() == dealerCard.getScore().getValue());

    private final double prizeMultiplier;
    private final BiFunction<Cards, Cards, Boolean> resultLogic;

    Result(double prizeMultiplier, BiFunction<Cards, Cards, Boolean> predicate) {
        this.prizeMultiplier = prizeMultiplier;
        this.resultLogic = predicate;
    }

    public static Result of(Cards userCard, Cards dealerCard) {
        return Arrays.stream(values())
                .filter(result -> result.resultLogic.apply(userCard, dealerCard))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException());
    }

    public int getProfit(final int stake) {
        return (int) (this.prizeMultiplier * stake);
    }
}
