package blackjack.domain.rule;

import blackjack.domain.DealerGameResult;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;

public class Judge {

    private static final Score BLACK_JACK = new Score(21);

    public DealerGameResult calculateDealerGameResult(Dealer dealer, Players players) {
        int playerWinCount = (int) players.getPlayers().stream()
                .filter(player -> isPlayerWin(dealer, player))
                .count();
        int dealerWinCount = players.countPlayer() - playerWinCount;
        return new DealerGameResult(dealerWinCount, playerWinCount);
    }

    public boolean isPlayerWin(Dealer dealer, Player player) {
        Score dealerScore = dealer.calculateHandScore();
        Score playerScore = player.calculateHandScore();
        if (playerScore.isAbove(BLACK_JACK)) {
            return false;
        }
        if (dealerScore.isAbove(BLACK_JACK)) {
            return true;
        }
        return playerScore.isAbove(dealerScore);
    }
}
