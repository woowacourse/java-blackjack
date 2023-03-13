package domain.game;

import domain.money.BettingMoneyTable;
import domain.money.Money;
import domain.card.Hand;
import domain.user.Dealer;
import domain.user.Player;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Exchanger {
    private final BettingMoneyTable bettingMoneyTable;
    private final Referee referee = new Referee();

    public Exchanger(BettingMoneyTable bettingMoneyTable) {
        this.bettingMoneyTable = bettingMoneyTable;
    }

    public Map<String, Money> getWinningMoneyOfPlayers(List<Player> players, Dealer dealer){
        Map<String, Money> winningMoneyOfPlayers = new HashMap<>();
        for (Player player : players) {
            Money winningMoney = getWinningMoneyOfPlayer(player, dealer);
            winningMoneyOfPlayers.put(player.getName(), winningMoney);
        }
        return winningMoneyOfPlayers;
    }

    public Money getWinningMoneyOfPlayer(Player player, Dealer dealer) {
        Hand playerHand = player.getHand();
        Hand dealerHand = dealer.getHand();
        Result result = referee.judgePlayerResult(playerHand, dealerHand);
        Money bettingMoney = bettingMoneyTable.findByPlayer(player);
        return bettingMoney.multiply(getExchangeRate(playerHand, result));
    }

    private double getExchangeRate(Hand playerHand, Result result) {
        if (playerHand.isBlackjack() && result.equals(Result.WIN)) {
            return 1.5;
        }
        if (result.equals(Result.WIN)) {
            return 1;
        }
        if (result.equals(Result.LOSE)) {
            return -1;
        }
        return 0;
    }

    public Money getWinningMoneyOfDealer(List<Money> winningMoneyOfPlayers) {
        int dealerWinningMoney = 0;
        for (Money winningMoneyOfPlayer : winningMoneyOfPlayers) {
            dealerWinningMoney += winningMoneyOfPlayer.multiply(-1).getValue();
        }
        return new Money(dealerWinningMoney);
    }
}
