package blackjack.domain;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Result {

    WIN((dealerResult, gamerResult) -> dealerResult < gamerResult),
    DRAW((dealerResult, gamerResult) -> dealerResult == gamerResult),
    LOSE((dealerResult, gamerResult) -> dealerResult > gamerResult);

    private final BiPredicate<Integer, Integer> predicate;

    Result(BiPredicate<Integer, Integer> predicate) {
        this.predicate = predicate;
    }

    public static Result findResult(final int dealerResult, final int gamerResult){
        return Arrays.stream(values())
                .filter((result) -> result.predicate.test(dealerResult, gamerResult))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하는 결과가 없습니다."));
    }
}
