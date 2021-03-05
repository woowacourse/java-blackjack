package blackjack.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public enum Result {
    WIN("승", 1),
    STANDOFF("무", 0),
    LOSE("패", -1);

    private final String result;
    private final int compareResult;

    Result(String result, int compareResult) {
        this.result = result;
        this.compareResult = compareResult;
    }

    public static boolean isExistBust(Dealer dealer, Player player) {
        return dealer.cards.isBust() || player.cards.isBust();
    }

    public static Result decideByBust(Dealer dealer, Player player) {
        if (dealer.cards.isBust() && !player.cards.isBust()) {
            return WIN;
        }
        if (!dealer.cards.isBust() && player.cards.isBust()) {
            return LOSE;
        }
        return STANDOFF;
    }

    public static Result decideByCompare(Dealer dealer, Player player) {
        return Arrays.stream(values())
                .filter(value -> value.compareResult == player.cards.compareTo(dealer.cards))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
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

    public static Map<Result, Integer> countByResults(List<Result> results) {
        return Arrays.stream(values())
                .collect(Collectors.toMap(result -> result, result -> result.count(results)));
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
