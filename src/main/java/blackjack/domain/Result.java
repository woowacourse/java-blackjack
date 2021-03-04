package blackjack.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public enum Result {
    WIN("승", 1),
    STAND_OFF("무", 0),
    LOSE("패", -1);

    private final String result;
    private final int compareValue;

    Result(String result, int compareValue) {
        this.result = result;
        this.compareValue = compareValue;
    }

    public static Result decide(Dealer dealer, Player player) {
        if (dealer.cards.isBust() && !player.cards.isBust()) {
            return WIN;
        }
        if (dealer.cards.isBust() && player.cards.isBust()) {
            return STAND_OFF;
        }
        if (!dealer.cards.isBust() && player.cards.isBust()) {
            return LOSE;
        }
        return Arrays.stream(values())
                .filter(value -> value.compareValue == player.cards.compareTo(dealer.cards))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public static Map<Result, Integer> countByResults(List<Result> results) {
        return Arrays.stream(values())
                .collect(Collectors.toMap(result -> result, result -> result.count(results)));
    }

    public Result reverse(Result result) {
        if (result.equals(WIN)) {
            return LOSE;
        }
        if (result.equals(LOSE)) {
            return WIN;
        }
        return result;
    }

    public int count(List<Result> results) {
        return (int) results.stream()
                .filter(this::equals)
                .count();
    }

    public String getResult() {
        return this.result;
    }
}
