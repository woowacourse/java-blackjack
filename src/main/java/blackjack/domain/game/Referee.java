package blackjack.domain.game;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;

import java.util.Map;

public class Referee {
    private final PlayersGameResult result;

    public Referee() {
        this.result = new PlayersGameResult();
    }

    public void calculatePlayersResults(Players players, Dealer dealer) {
        for (Player player : players.getPlayers()) {
            result.put(player, judgeGameResult(dealer, player));
        }
    }

    private PlayerWinStatus judgeGameResult(Dealer dealer, Player player) {
        if (player.isBust() || dealer.isBlackjack()) {
            return PlayerWinStatus.LOSE;
        }
        if (isPlayerWin(dealer, player)) {
            return PlayerWinStatus.WIN;
        }
        if (dealer.getScore() == player.getScore()) {
            return PlayerWinStatus.PUSH;
        }
        return PlayerWinStatus.LOSE;
    }

    private boolean isPlayerWin(Dealer dealer, Player player) {
        return player.getScore() > dealer.getScore() ||
                player.isBlackjack() ||
                dealer.isBust();
    }

    public Map<String, PlayerWinStatus> getPlayersNameAndResults() {
        return result.getPlayersNameAndResults();
    }

    public Map<PlayerWinStatus, Integer> getDealerResult() {
        return result.getDealerResult();
    }
}
