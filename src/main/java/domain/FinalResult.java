package domain;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum FinalResult {
    WIN("승", (sumOfRank, otherSumOfRank) -> sumOfRank > otherSumOfRank),
    LOSE("패", (sumOfRank, otherSumOfRank) -> sumOfRank < otherSumOfRank),
    DRAW("무", Integer::equals);

    private final String title;
    private final BiPredicate<Integer, Integer> condition;

    FinalResult(final String title, final BiPredicate<Integer, Integer> condition) {
        this.title = title;
        this.condition = condition;
    }

    public static FinalResult findBySumOfRank(final int sumOfRank, final int otherSumOfRank) {
        return Arrays.stream(FinalResult.values())
                .filter(finalResult -> finalResult.condition.test(sumOfRank, otherSumOfRank))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("비 정상적인 상태입니다."));
    }

    public static Map<Player, FinalResult> makePlayerResult(final List<Player> players, final Dealer dealer) {
        return players.stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        player -> {
                            final int playerSum = player.calculateSumOfRank();
                            final int dealerSum = dealer.calculateSumOfRank();
                            return Optional.of(playerSum)
                                    .map(sum -> getIntegerFinalResultFunction(sum, dealerSum))
                                    .orElse(LOSE);
                        }
                ));
    }

    private static FinalResult getIntegerFinalResultFunction(final int playerSum, final int dealerSum) {
        if (playerSum > 21) {
            return LOSE;
        }

        if (dealerSum > 21) {
            return WIN;
        }
        return FinalResult.findBySumOfRank(playerSum, dealerSum);
    }

    public static Map<FinalResult, Integer> makeDealerResult(final Map<Player, FinalResult> playerResults) {
        return playerResults.values().stream()
                .collect(Collectors.toMap(
                        finalResult -> finalResult,
                        finalResult -> 1,
                        Integer::sum,
                        () -> new EnumMap<>(FinalResult.class)
                ));
    }

    public String getTitle() {
        return title;
    }
}
