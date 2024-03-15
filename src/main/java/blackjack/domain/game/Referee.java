package blackjack.domain.game;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;

import java.util.LinkedHashMap;
import java.util.Map;

public class Referee {
    private final Map<Player, PlayerWinStatus> playersWinStatus;

    public Referee() {
        this.playersWinStatus = new LinkedHashMap<>();
    }

    public void calculatePlayerResult(Dealer dealer, Player player) {
        playersWinStatus.put(player, judgePlayerResult(dealer, player));
    }

    public PlayerWinStatus judgePlayerResult(Dealer dealer, Player player) {
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

    public PlayerWinStatus findPlayerResult(Player player) {
        return playersWinStatus.get(player);
    }
}
