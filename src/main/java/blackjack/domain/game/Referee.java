package blackjack.domain.game;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;

import java.util.Map;

public class Referee {
    // TODO: 결과를 플레이어와 딜러에서 계산 후 바로 반환하도 좋을듯
    private final PlayersGameResult result;

    public Referee() {
        this.result = new PlayersGameResult();
    }

    public void calculatePlayersResults(Players players, Dealer dealer) {
        for (Player player : players.getPlayers()) {
            result.put(player, judgeGameResult(dealer, player));
        }
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
