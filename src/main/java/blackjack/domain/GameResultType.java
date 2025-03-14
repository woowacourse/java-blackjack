package blackjack.domain;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum GameResultType {

    WIN((value, comparedValue) -> value > comparedValue),
    TIE(Integer::equals),
    LOSE((value, comparedValue) -> value < comparedValue),
    ;

    private final BiPredicate<Integer, Integer> condition;

    GameResultType(BiPredicate<Integer, Integer> condition) {
        this.condition = condition;
    }

    public static GameResultType judgeForPlayer(Player player, Dealer dealer) {
        if (player.isBlackjack() || dealer.isBlackjack()) {
            return judgeForPlayerWithBlackjack(player, dealer);
        }

        int playerOptimisticValue = player.getOptimisticValue();
        int dealerOptimisticValue = dealer.getOptimisticValue();

        return Arrays.stream(GameResultType.values())
                .filter(resultType ->
                        resultType.condition.test(playerOptimisticValue, dealerOptimisticValue))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 비교 값입니다."));
    }

    private static GameResultType judgeForPlayerWithBlackjack(Player player, Dealer dealer) {
        if (player.isBlackjack() && dealer.isBlackjack()) {
            return GameResultType.TIE;
        }

        if (player.isBlackjack()) {
            return GameResultType.WIN;
        }

        return GameResultType.LOSE;
    }
}
