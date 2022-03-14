package domain;

import domain.card.Cards;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

public enum GameResult {
    WIN((a, b) -> compareCardStatePower(a, b) > 0 || (isBothStand(a, b) && compareCardScore(a, b) > 0)),
    DRAW((a, b) -> isBothBlackJack(a, b) || (isBothStand(a, b) && compareCardScore(a, b) == 0)),
    LOSE((a, b) -> a.isBust() || compareCardStatePower(a, b) < 0 || (isBothStand(a, b) && compareCardScore(a, b) < 0));

    private static final String CANNOT_FIND_GAME_RESULT_MESSAGE = "게임결과를 찾을 수 없습니다.";

    private final BiPredicate<Cards, Cards> biPredicate;

    GameResult(final BiPredicate<Cards, Cards> biPredicate) {
        this.biPredicate = biPredicate;
    }

    public static GameResult of(final Cards playerCards, final Cards dealerCards) {
        return Arrays.stream(GameResult.values())
                .filter(gameResult -> gameResult.biPredicate.test(playerCards, dealerCards))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(CANNOT_FIND_GAME_RESULT_MESSAGE));
    }

    private static int compareCardStatePower(final Cards playerCards, final Cards dealerCards) {
        return playerCards.getCardStatePower() - dealerCards.getCardStatePower();
    }

    private static boolean isBothStand(final Cards playerCards, final Cards dealerCards) {
        return playerCards.isStand() && dealerCards.isStand();
    }

    private static boolean isBothBlackJack(final Cards playerCards, final Cards dealerCards) {
        return playerCards.isBlackJack() && dealerCards.isBlackJack();
    }

    private static int compareCardScore(final Cards playerCards, final Cards dealerCards) {
        return playerCards.calculateSum() - dealerCards.calculateSum();
    }

    public static List<GameResult> reverseResults(final List<GameResult> origin) {
        return origin.stream()
                .map(GameResult::reverseResults)
                .collect(Collectors.toList());
    }

    private static GameResult reverseResults(final GameResult origin) {
        if (origin == WIN) {
            return LOSE;
        }
        if (origin == LOSE) {
            return WIN;
        }
        return DRAW;
    }
}
