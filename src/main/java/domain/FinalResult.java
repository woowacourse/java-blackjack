package domain;

import static domain.gamer.Hand.BUST_NUMBER;

import domain.gamer.Dealer;
import domain.gamer.Player;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum FinalResult {
    WIN((sumOfRank, otherSumOfRank) -> sumOfRank > otherSumOfRank),
    LOSE((sumOfRank, otherSumOfRank) -> sumOfRank < otherSumOfRank),
    DRAW(Integer::equals);

    private final BiPredicate<Integer, Integer> condition;

    FinalResult(final BiPredicate<Integer, Integer> condition) {
        this.condition = condition;
    }

    public static Map<Player, FinalResult> makePlayerResult(final List<Player> players, final Dealer dealer) {
        return players.stream()
                .collect(Collectors.toMap(Function.identity(), player -> getFinalResult(player, dealer)));
    }

    private static FinalResult getFinalResult(final Player player, final Dealer dealer) {
        final int playerSum = player.calculateSumOfRank();
        final int dealerSum = dealer.calculateSumOfRank();
        return getIntegerFinalResultFunction(playerSum, dealerSum);
    }

    private static FinalResult getIntegerFinalResultFunction(final int playerSum, final int dealerSum) {
        if (playerSum > BUST_NUMBER) {
            return LOSE;
        }

        if (dealerSum > BUST_NUMBER) {
            return WIN;
        }
        return FinalResult.findBySumOfRank(playerSum, dealerSum);
    }

    private static FinalResult findBySumOfRank(final int sumOfRank, final int otherSumOfRank) {
        return Arrays.stream(FinalResult.values())
                .filter(finalResult -> finalResult.condition.test(sumOfRank, otherSumOfRank))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("플레이어의 합계와 딜러의 합계에 해당하는 결과를 찾을 수 없습니다."));
    }

    public static Map<FinalResult, Integer> makeDealerResult(final Map<Player, FinalResult> playerResults) {
        return playerResults.values()
                .stream()
                .collect(Collectors.toMap(
                        finalResult -> finalResult,
                        finalResult -> 1,
                        Integer::sum,
                        () -> new EnumMap<>(FinalResult.class)
                ));
    }
}
