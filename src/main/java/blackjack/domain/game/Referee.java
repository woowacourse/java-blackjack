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
        players.forEach(player ->
                result.put(player, judgeGameResult(dealer, player))
        );
    }

    private PlayerGameResult judgeGameResult(Dealer dealer, Player player) {
        if (player.isBust() || dealer.isBlackjack()) {
            return PlayerGameResult.LOSE;
        }
        if (isPlayerWin(dealer, player)) {
            return PlayerGameResult.WIN;
        }
        if (dealer.getScore() == player.getScore()) {
            return PlayerGameResult.PUSH;
        }
        return PlayerGameResult.LOSE;
    }

    private boolean isPlayerWin(Dealer dealer, Player player) {
        return player.getScore() > dealer.getScore() ||
                player.isBlackjack() ||
                dealer.isBust();
    }

    public Map<String, PlayerGameResult> getPlayersNameAndResults() {
        return result.getPlayersNameAndResults();
    }

    public Map<PlayerGameResult, Integer> getDealerResult() {
        return result.getDealerResult();
    }
}
