package blackjack.domain.bet;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
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

    public double calculatePlayerProfit(Dealer dealer, Player player) {
        if (dealer.isBlackjack() || player.isBlackjack()) {
            return checkBlackjack(dealer, player);
        }
        if (dealer.isBust() || player.isBust()) {
            return checkBust(dealer, player);
        }
        return checkScore(dealer, player);
    }

    private double checkBlackjack(Dealer dealer, Player player) {
        if (dealer.isBlackjack() && player.isBlackjack()) {
            return toBetMoney(player) * BetRate.DRAW.getRate();
        }
        if (player.isBlackjack()) {
            return toBetMoney(player) * BetRate.BLAKJACK.getRate();
        }
        return toBetMoney(player) * BetRate.LOSE.getRate();
    }

    private double checkBust(Dealer dealer, Player player) {
        if (dealer.isBust() && player.isBust()) {
            return toBetMoney(player) * BetRate.DRAW.getRate();
        }
        if (player.isBust()) {
            return toBetMoney(player) * BetRate.LOSE.getRate();
        }
        return toBetMoney(player) * BetRate.WIN.getRate();
    }

    private double checkScore(Dealer dealer, Player player) {
        int gap = player.getScore() - dealer.getScore();
        if (gap > 0) {
            return toBetMoney(player) * BetRate.WIN.getRate();
        }
        if (gap < 0) {
            return toBetMoney(player) * BetRate.LOSE.getRate();
        }
        return toBetMoney(player) * BetRate.DRAW.getRate();
    }

    private int toBetMoney(Player player) {
        return playerNameAndBets.get(player.getName().get()).get();
    }
}
