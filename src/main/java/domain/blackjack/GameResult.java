package domain.blackjack;

import domain.player.Player;
import domain.player.PlayerResult;
import java.util.Map;

public record GameResult(Map<PlayerResult, Integer> dealerResult,
                         Map<Player, PlayerResult> playerResult) {

    public int dealerWin() {
        if (dealerResult.containsKey(PlayerResult.WIN)) {
            return dealerResult.get(PlayerResult.WIN);
        }
        return 0;
    }

    public int dealerLose() {
        if (dealerResult.containsKey(PlayerResult.LOSE)) {
            return dealerResult.get(PlayerResult.LOSE);
        }
        return 0;
    }

    public int dealerTie() {
        if (dealerResult.containsKey(PlayerResult.TIE)) {
            return dealerResult.get(PlayerResult.TIE);
        }
        return 0;
    }

    public PlayerResult playerResult(final Player player) {
        if(playerResult.containsKey(player)){
            return playerResult.get(player);
        }
        throw new IllegalArgumentException("존재하지 않는 플레이어입니다.");
    }

}
