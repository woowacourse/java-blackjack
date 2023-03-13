package domain;

import domain.bettingMoney.BettingMoneyTable;
import domain.bettingMoney.Money;
import domain.card.Hand;
import domain.game.Referee;
import domain.game.Result;
import domain.user.Dealer;
import domain.user.Player;
import java.util.List;

public class Exchanger {
    private final BettingMoneyTable bettingMoneyTable;
    private final Referee referee = new Referee();

    public Exchanger(BettingMoneyTable bettingMoneyTable) {
        this.bettingMoneyTable = bettingMoneyTable;
    }

    public Money getPlayerWinningMoney(Player player, Dealer dealer) {
        Hand playerHand = player.getHand();
        Hand dealerHand = dealer.getHand();
        Result result = Referee.judgePlayerResult(playerHand, dealerHand);
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

    public Money getDealerWinningMoney(List<Money> winningMoneyOfPlayers) {
        int dealerWinningMoney = 0;
        for (Money winningMoneyOfPlayer : winningMoneyOfPlayers) {
            dealerWinningMoney += winningMoneyOfPlayer.multiply(-1).getValue();
        }
        return new Money(dealerWinningMoney);
    }
}
