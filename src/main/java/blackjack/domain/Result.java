package blackjack.domain;

import java.util.function.BiPredicate;
import java.util.stream.Stream;

public enum Result {
    WIN("승", Result::isWin),
    LOSE("패", Result::isLose),
    DRAW("무", Result::isDraw),
    ;
    private final String message;
    private final BiPredicate<Integer, Integer> predicate;

    Result(String message, BiPredicate<Integer, Integer> predicate) {
        this.message = message;
        this.predicate = predicate;
    }

    public static Result getInstance(int playerPoint, int dealerPoint) {
        return Stream.of(Result.values())
            .filter(it -> it.predicate.test(playerPoint, dealerPoint))
            .findAny()
            .orElseThrow(IllegalArgumentException::new);
    }

    public String getMessage() {
        return message;
    }

    private static boolean isWin(int playerPoint, int dealerPoint) {
        return (playerPoint > Cards.HIGHEST_POINT && dealerPoint <= Cards.HIGHEST_POINT) || (
            playerPoint < dealerPoint);
    }

    private static boolean isLose(int playerPoint, int dealerPoint) {
        return (dealerPoint > Cards.HIGHEST_POINT && playerPoint <= Cards.HIGHEST_POINT) || (
            playerPoint > dealerPoint);
    }

    private static boolean isDraw(int playerPoint, int dealerPoint) {
        return (playerPoint > Cards.HIGHEST_POINT && dealerPoint > Cards.HIGHEST_POINT) || (
            playerPoint == dealerPoint);
    }


}
