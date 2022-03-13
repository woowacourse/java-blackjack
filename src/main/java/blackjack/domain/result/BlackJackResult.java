package blackjack.domain.result;

import java.util.Arrays;
import java.util.function.BiPredicate;

import static blackjack.domain.gamer.Gamer.MAX_CARD_VALUE;

public enum BlackJackResult {

    WIN("승", (player, dealer) ->
            (player <= MAX_CARD_VALUE && (player > dealer || dealer > MAX_CARD_VALUE))),
    LOSE("패", (player, dealer) ->
            (player > MAX_CARD_VALUE) || (dealer <= MAX_CARD_VALUE && player < dealer)),
    DRAW("무", (player, dealer) ->
            (player <= MAX_CARD_VALUE && dealer <= MAX_CARD_VALUE && player.equals(dealer)));

    private static final String NOT_EXIST_ERROR = "옯바른 결과를 찾을 수 없습니다.";

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
                .orElseThrow(() -> new IllegalArgumentException(NOT_EXIST_ERROR));
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
