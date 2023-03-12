package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public enum ResultState {
    WIN_BLACKJACK(1.5),
    WIN(1),
    LOSE(-1),
    PUSH(0);

    private final double times;

    ResultState(double times) {
        this.times = times;
    }

    public static ResultState of(Player player, Dealer dealer) {
        ScoreState dealerState = dealer.getState();
        if (dealerState.isBlackjack()) {
            return getResultDealerBlackjack(player);
        }
        if (dealerState.isBust()) {
            return getResultDealerBust(player);
        }
        return getResultDealerOther(player, dealer);
    }

    private static ResultState getResultDealerBlackjack(Player player) {
        ScoreState playerState = player.getState();
        if (playerState.isBlackjack()) {
            return ResultState.PUSH;
        }
        return ResultState.LOSE;
    }

    private static ResultState getResultDealerBust(Player player) {
        ScoreState playerState = player.getState();
        if (playerState.isBust()) {
            return ResultState.LOSE;
        }
        return ResultState.WIN;
    }

    private static ResultState getResultDealerOther(Player player, Dealer dealer) {
        ScoreState playerState = player.getState();
        if (playerState.isBlackjack()) {
            return WIN_BLACKJACK;
        }
        if (player.canHit() && player.getScore() > dealer.getScore()) {
            return ResultState.WIN;
        }
        if (player.getScore() == dealer.getScore()) {
            return ResultState.PUSH;
        }
        return ResultState.LOSE;
    }

    public double getTimes() {
        return times;
    }
}
