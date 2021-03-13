package blackjack.domain.result;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Money;
import blackjack.domain.participant.Player;
import java.util.ArrayList;
import java.util.List;

public class GameResultDto {

    private final List<PlayerResultDto> playerResults;
    private final int dealerWinningMoney;

    public GameResultDto(List<PlayerResultDto> playerResults, int dealerWinningMoney) {
        this.playerResults = playerResults;
        this.dealerWinningMoney = dealerWinningMoney;
    }

    public static GameResultDto calculate(Dealer dealer, List<Player> players) {
        List<PlayerResultDto> playerResults = new ArrayList<>();
        Money dealerWinningMoney = Money.ZERO;

        for (Player player : players) {
            Money winningMoney = player.getWinningMoney(dealer);
            playerResults.add(PlayerResultDto.of(player, winningMoney));

            dealerWinningMoney = dealerWinningMoney.add(winningMoney.toNegative());
        }

        return new GameResultDto(playerResults, dealerWinningMoney.toInt());
    }

    public List<PlayerResultDto> getPlayerResults() {
        return playerResults;
    }

    public int getDealerWinningMoney() {
        return dealerWinningMoney;
    }
}
