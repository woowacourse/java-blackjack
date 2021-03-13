package blackjack.domain.result;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Money;
import blackjack.domain.participant.Player;
import java.util.ArrayList;
import java.util.List;

public class GameResult {

    private final List<PlayerResult> playersResults;
    private final int dealerWinningMoney;

    public GameResult(List<PlayerResult> playersResults, int dealerWinningMoney) {
        this.playersResults = playersResults;
        this.dealerWinningMoney = dealerWinningMoney;
    }

    public static GameResult calculate(Dealer dealer, List<Player> players) {
        List<PlayerResult> playersResults = new ArrayList<>();
        Money dealerWinningMoney = Money.ZERO;

        for (Player player : players) {
            Money winningMoney = player.getWinningMoney(dealer);
            playersResults.add(PlayerResult.of(player, winningMoney));

            dealerWinningMoney = dealerWinningMoney.add(winningMoney.toNegative());
        }

        return new GameResult(playersResults, dealerWinningMoney.toInt());
    }

    public List<PlayerResult> getPlayersResults() {
        return playersResults;
    }

    public int getDealerWinningMoney() {
        return dealerWinningMoney;
    }
}
