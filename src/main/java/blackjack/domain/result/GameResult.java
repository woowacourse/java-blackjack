package blackjack.domain.result;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Money;
import blackjack.domain.participant.Player;
import java.util.ArrayList;
import java.util.List;

public class GameResult {

    private final List<PlayerResult> playersResults;
    private final Money dealerWinningMoney;

    public GameResult(List<PlayerResult> playersResults, Money dealerWinningMoney) {
        this.playersResults = playersResults;
        this.dealerWinningMoney = dealerWinningMoney;
    }

    public static GameResult calculate(Dealer dealer, List<Player> players) {
        List<PlayerResult> playersResults = new ArrayList<>();
        Money dealerWinningMoney = Money.ZERO;

        for (Player player : players) {
            Money winningMoney = player.getWinningMoney(dealer);
            playersResults.add(PlayerResult.from(player, winningMoney));

            dealerWinningMoney = dealerWinningMoney.add(winningMoney.toNegative());
        }

        return new GameResult(playersResults, dealerWinningMoney);
    }

    public List<PlayerResult> getPlayersResults() {
        return playersResults;
    }

    public Money getDealerWinningMoney() {
        return dealerWinningMoney;
    }
}
