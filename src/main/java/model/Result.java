package model;

import java.util.Arrays;
import java.util.function.BiPredicate;
import model.card.Cards;

public enum Result {
    WIN((player, dealer) -> player.compareTo(dealer) > 0,
            (playerSum, dealerSum) -> playerSum > dealerSum),
    LOSE((player, dealer) -> (player.equals(Status.BUST) && dealer.equals(Status.BUST)) || player.compareTo(dealer) < 0,
            (playerSum, dealerSum) -> playerSum < dealerSum),
    DRAW((player, dealer) -> !player.equals(Status.BUST) && player.compareTo(dealer) == 0,
            (playerSum, dealerSum) -> playerSum.equals(dealerSum));

    private final BiPredicate<Status, Status> statusCriteria;
    private final BiPredicate<Integer, Integer> cardsSumCriteria;

    Result(BiPredicate<Status, Status> statusCriteria,
           BiPredicate<Integer, Integer> cardsSumCriteria) {
        this.statusCriteria = statusCriteria;
        this.cardsSumCriteria = cardsSumCriteria;
    }

    public static Result of(Cards playerCards, Cards dealerCards) {
        if (playerCards.isStand() && dealerCards.isStand()) {
            return Result.of(playerCards.getSum(), dealerCards.getSum());
        }
        return Result.of(playerCards.getStatus(), dealerCards.getStatus());
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
}
