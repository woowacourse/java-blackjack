package model;

import java.util.Arrays;
import java.util.function.BiPredicate;
import model.participator.Dealer;
import model.participator.Player;

public enum Result {
    WIN((player, dealer) -> player.compareTo(dealer) > 0 || dealer.equals(Status.BUST),
            (playerSum, dealerSum) -> playerSum > dealerSum, 1),
    LOSE((player, dealer) -> player.compareTo(dealer) < 0,
            (playerSum, dealerSum) -> playerSum < dealerSum, -1),
    DRAW((player, dealer) -> !player.equals(Status.BUST) && player.compareTo(dealer) == 0,
            Integer::equals, 0);

    private final BiPredicate<Status, Status> statusCriteria;
    private final BiPredicate<Integer, Integer> cardsSumCriteria;
    private final int playerBettingIncrease;

    Result(BiPredicate<Status, Status> statusCriteria,
           BiPredicate<Integer, Integer> cardsSumCriteria,
           int playerBettingIncrease) {
        this.statusCriteria = statusCriteria;
        this.cardsSumCriteria = cardsSumCriteria;
        this.playerBettingIncrease = playerBettingIncrease;
    }

    public static Result of(Player player, Dealer dealer) {
        Status playerStatus = player.getStatus();
        Status dealerStatus = dealer.getStatus();
        if (playerStatus.equals(Status.STAND) && dealerStatus.equals(Status.STAND)) {
            return Result.of(player.getSum(), dealer.getSum());
        }
        return Result.of(playerStatus, dealerStatus);
    }

    private static Result of(Status playerStatus, Status dealerStatus) {
        return Arrays.stream(values())
                .filter(result -> result.statusCriteria.test(playerStatus, dealerStatus))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("승부 결과를 내지 못했습니다."));
    }

    private static Result of(int playerSum, int dealerSum) {
        return Arrays.stream(values())
                .filter(result -> result.cardsSumCriteria.test(playerSum, dealerSum))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("승부 결과를 내지 못했습니다."));
    }

    public Result getOpposite() {
        if (this.equals(WIN)) {
            return LOSE;
        }
        if (this.equals(LOSE)) {
            return WIN;
        }
        return this;
    }

    public long getEarnedAmount(long amount) {
        return this.playerBettingIncrease * amount;
    }
}
