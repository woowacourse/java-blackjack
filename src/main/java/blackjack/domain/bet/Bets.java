package blackjack.domain.bet;

import blackjack.domain.user.User;
import java.util.Map;

public class Bets {

    private final Map<String, BetMoney> playerNameAndBets;

    public Bets(Map<String, BetMoney> playerNameAndBets) {
        this.playerNameAndBets = playerNameAndBets;
    }

    public int calculateDealerProfit(Map<String, Integer> playersProfit) {
        return (-1) * playersProfit.values().stream()
                .mapToInt(profit -> profit)
                .sum();
    }

    public double calculatePlayerProfit(User dealer, User player) {
        if (dealer.isBlackjack() || player.isBlackjack()) {
            return checkBlackjack(dealer, player);
        }
        if (dealer.isBust() || player.isBust()) {
            return checkBust(dealer, player);
        }
        return checkScore(dealer, player);
    }

    private double checkBlackjack(User dealer, User player) {
        if (dealer.isBlackjack() && player.isBlackjack()) {
            return toBetMoney(player) * BetRate.DRAW.getRate();
        }
        if (player.isBlackjack()) {
            return toBetMoney(player) * BetRate.BLAKJACK.getRate();
        }
        return toBetMoney(player) * BetRate.LOSE.getRate();
    }

    private double checkBust(User dealer, User player) {
        if (dealer.isBust() && player.isBust()) {
            return toBetMoney(player) * BetRate.DRAW.getRate();
        }
        if (player.isBust()) {
            return toBetMoney(player) * BetRate.LOSE.getRate();
        }
        return toBetMoney(player) * BetRate.WIN.getRate();
    }

    private double checkScore(User dealer, User player) {
        int gap = player.getScore() - dealer.getScore();
        if (gap > 0) {
            return toBetMoney(player) * BetRate.WIN.getRate();
        }
        if (gap < 0) {
            return toBetMoney(player) * BetRate.LOSE.getRate();
        }
        return toBetMoney(player) * BetRate.DRAW.getRate();
    }

    private int toBetMoney(User player) {
        return playerNameAndBets.get(player.getName().get()).get();
    }
}
