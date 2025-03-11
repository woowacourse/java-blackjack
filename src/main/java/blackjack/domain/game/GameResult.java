package blackjack.domain.game;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import java.util.List;

public final class GameResult {

    private final List<PlayerWinning> playerWinnings;

    public GameResult(Dealer dealer, List<Player> players) {
        this.playerWinnings = players.stream()
                .map(player -> new PlayerWinning(dealer, player))
                .toList();
    }

    public int getDealerWinningState(WinningType winningType) {
        return (int) playerWinnings.stream()
                .filter(playerWinning -> playerWinning.getWinningType().equals(winningType.reverse()))
                .count();
    }

    public List<PlayerWinning> getPlayerWinnings() {
        return playerWinnings;
    }
}
