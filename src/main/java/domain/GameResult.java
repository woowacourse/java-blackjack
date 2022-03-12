package domain;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum GameResult {
    WIN((a, b) -> compareCardStatePower(a, b) > 0 || (isBothStand(a, b) && compareCardScore(a, b) > 0)),
    DRAW((a, b) -> isBothBlackJack(a, b) || (isBothStand(a, b) && compareCardScore(a, b) == 0)),
    LOSE((a, b) -> a.isBust() || compareCardStatePower(a, b) < 0 || (isBothStand(a, b) && compareCardScore(a, b) < 0));

    private final BiPredicate<Cards, Cards> biPredicate;

    GameResult(BiPredicate<Cards, Cards> biPredicate) {
        this.biPredicate = biPredicate;
    }

    public static GameResult of(Cards playerCards, Cards dealerCards) {
        return Arrays.stream(GameResult.values())
                .filter(gameResult -> gameResult.biPredicate.test(playerCards, dealerCards))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    private static int compareCardStatePower(Cards playerCards, Cards dealerCards) {
        return playerCards.getCardStatePower() - dealerCards.getCardStatePower();
    }

    private static boolean isBothStand(Cards playerCards, Cards dealerCards) {
        return playerCards.isStand() && dealerCards.isStand();
    }

    private static int compareCardScore(Cards playerCards, Cards dealerCards) {
        return playerCards.calculateSum() - dealerCards.calculateSum();
    }

    private static boolean isBothBlackJack(Cards playerCards, Cards dealerCards) {
        return playerCards.isBlackJack() && dealerCards.isBlackJack();
    }
}
