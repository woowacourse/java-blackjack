package blackjack.domain.result;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum BlackJackResult {

    WIN((player, dealer) -> (player <= 21 && player > dealer) || (player <= 21 && dealer > 21)),
    LOSE((player, dealer) -> (player > 21) || (dealer <= 21 && player < dealer)),
    DRAW((player, dealer) -> (player <= 21 && dealer <= 21 && player.equals(dealer)));

    private final BiPredicate<Integer, Integer> predicate;

    BlackJackResult(BiPredicate<Integer, Integer> predicate) {
        this.predicate = predicate;
    }

    public static BlackJackResult of(Integer point, Integer otherPoint) {
        return Arrays.stream(values())
                .filter(result -> result.predicate.test(point, otherPoint))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("옯바른 결과를 찾을 수 없습니다."));
    }

    public BlackJackResult getReverse() {
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return DRAW;
    }
}
