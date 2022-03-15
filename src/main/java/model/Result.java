package model;

import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import model.participator.Dealer;
import model.participator.Player;

public enum Result {
    WIN((player, dealer) -> player.compareTo(dealer) > 0 || dealer.equals(Status.BUST),
            (playerSum, dealerSum) -> playerSum > dealerSum,
            Player::winBet),
    LOSE((player, dealer) -> player.compareTo(dealer) < 0,
            (playerSum, dealerSum) -> playerSum < dealerSum,
            (Player::lostBet)),
    DRAW((player, dealer) -> !player.equals(Status.BUST) && player.compareTo(dealer) == 0,
            Integer::equals,
            ((player, dealer) -> {}));

    private final BiPredicate<Status, Status> statusCriteria;
    private final BiPredicate<Integer, Integer> cardsSumCriteria;
    private final BiConsumer<Player, Dealer> bettingExecution;

    Result(BiPredicate<Status, Status> statusCriteria,
           BiPredicate<Integer, Integer> cardsSumCriteria,
           BiConsumer<Player, Dealer> bettingExecution) {
        this.statusCriteria = statusCriteria;
        this.cardsSumCriteria = cardsSumCriteria;
        this.bettingExecution = bettingExecution;
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

    public void executeBetting(Player player, Dealer dealer) {
        this.bettingExecution.accept(player, dealer);
    }
}
