package blackJack.domain.result;

import blackJack.domain.money.Bet;
import blackJack.domain.participant.Dealer;
import blackJack.domain.participant.Player;

public enum OutCome {
    BLACKJACK(1.5),
    WIN(1),
    DRAW(0),
    LOSE(-1);

    private final double profit;

    OutCome(double profit) {
        this.profit = profit;
    }

    public static OutCome ofJudge(Dealer dealer, Player player) {
        if (dealer.isBust() || player.isBust()) {
            return getOutComeOfBust(player);
        }
        if (dealer.isBlackJack() || player.isBlackJack()) {
            return getOutComeOfBlackjack(dealer, player);
        }
        return getOutComeOfScore(dealer, player);
    }

    private static OutCome getOutComeOfBust(Player player) {
        if (player.isBust()) {
            return LOSE;
        }
        return WIN;
    }

    private static OutCome getOutComeOfBlackjack(Dealer dealer, Player player) {
        if (!dealer.isBlackJack() && player.isBlackJack()) {
            return BLACKJACK;
        }
        if (dealer.isBlackJack() && player.isBlackJack()) {
            return DRAW;
        }
        return LOSE;
    }

    private static OutCome getOutComeOfScore(Dealer dealer, Player player) {
        if (dealer.getScore() < player.getScore()) {
            return WIN;
        }
        if (dealer.getScore() == player.getScore()) {
            return DRAW;
        }
        return LOSE;
    }

    public int calculateEarning(Bet bet) {
        return (int) (bet.getValue() * profit);
    }
}
