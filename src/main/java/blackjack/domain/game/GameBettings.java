package blackjack.domain.game;

import blackjack.domain.gameresult.Betting;
import blackjack.domain.participant.Player;

import java.util.Map;

public class GameBettings {
    private final Map<Player, Betting> gameBettings;

    public GameBettings(Map<Player, Betting> gameBettings) {
        this.gameBettings = gameBettings;
    }

    public Betting findPlayerBatting(Player player) {
        if (gameBettings.containsKey(player)) {
            return gameBettings.get(player);
        }
        throw new IllegalStateException("조회한 플레이어의 베팅액이 없습니다.");
    }
}
