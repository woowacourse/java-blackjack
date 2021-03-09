package blackjack.domain.result;

import blackjack.domain.user.User;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

public enum Result {
    WIN("승", (playerNotBust, dealerNotBust) -> playerNotBust && !dealerNotBust,
            (playerScore, dealerScore) -> playerScore > dealerScore),
    STAND_OFF("무", (playerNotBust, dealerNotBust) -> !playerNotBust && !dealerNotBust,
            Integer::equals),
    LOSE("패", (playerNotBust, dealerNotBust) -> !playerNotBust && dealerNotBust,
            (playerScore, dealerScore) -> playerScore < dealerScore);

    private final String result;
    private final BiPredicate<Boolean, Boolean> statusPredicate;
    private final BiPredicate<Integer, Integer> scorePredicate;

    Result(String result, BiPredicate<Boolean, Boolean> statusPredicate,
           BiPredicate<Integer, Integer> scorePredicate) {
        this.result = result;
        this.statusPredicate = statusPredicate;
        this.scorePredicate = scorePredicate;
    }

    public static Result decide(User player, User dealer) {
        return Arrays.stream(values())
                .filter(value -> value.statusPredicate.test(player.isAbleToHit(), dealer.isAbleToHit()))
                .findFirst()
                .orElseGet(() -> decideByScore(player.score(), dealer.score()));
    }

    private static Result decideByScore(int playerScore, int dealerScore) {
        return Arrays.stream(values())
                .filter(value -> value.scorePredicate.test(playerScore, dealerScore))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("승패 결과 조건에 매치되지 않습니다."));
    }

    public static LinkedHashMap<Result, Integer> countByResults(List<Result> results) {
        return Arrays.stream(values())
                .collect(Collectors.toMap(result -> result,
                        result -> result.count(results),
                        (p1, p2) -> p1,
                        LinkedHashMap::new));
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

    private int count(List<Result> results) {
        return (int) results.stream()
                .filter(this::equals)
                .count();
    }

    public String getResult() {
        return this.result;
    }
}
