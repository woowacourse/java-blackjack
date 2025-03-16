package domain.result;

import domain.participant.Dealer;
import domain.participant.Player;

import java.util.function.Function;

public enum ResultStatus {
    WIN(betAmount -> betAmount),
    LOSE(betAmount -> -betAmount),
    PUSH(betAmount -> 0),
    BLACK_JACK(betAmount -> (int) (betAmount * 1.5));

    private final Function<Integer, Integer> calculateProfit;

    ResultStatus(Function<Integer, Integer> calculateProfit) {
        this.calculateProfit = calculateProfit;
    }

    public static ResultStatus judgeGameResult(Player player, Dealer dealer) {
        if (player.isBurst()) return ResultStatus.LOSE;
        if (player.isBlackJack()) return ResultStatus.BLACK_JACK;
        if (dealer.isBurst()) return ResultStatus.WIN;
        return compareDifference(player, dealer);
    }

    private static ResultStatus compareDifference(Player player, Dealer dealer) {
        int dealerAbs = dealer.calculateDifferenceFromTwentyOne();
        int playerAbs = player.calculateDifferenceFromTwentyOne();

        if (playerAbs > dealerAbs) return ResultStatus.LOSE;
        if (playerAbs == dealerAbs) return ResultStatus.PUSH;
        return ResultStatus.WIN;
    }

    public int calculateIncome(int betAmount) {
        return calculateProfit.apply(betAmount);
    }
}
