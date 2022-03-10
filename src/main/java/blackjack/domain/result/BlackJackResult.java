package blackjack.domain.result;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum BlackJackResult {

    WIN("승", (player, dealer) -> (player <= 21 && player > dealer) || (player <= 21 && dealer > 21)),
    LOSE("패", (player, dealer) -> (player > 21) || (dealer <= 21 && player < dealer)),
    DRAW("무", (player, dealer) -> (player <= 21 && dealer <= 21 && player.equals(dealer)));

    private final String value;
    private final BiPredicate<Integer, Integer> predicate;

    BlackJackResult(String value, BiPredicate<Integer, Integer> predicate) {
        this.value = value;
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

    public String getValue() {
        return value;
    }
}
