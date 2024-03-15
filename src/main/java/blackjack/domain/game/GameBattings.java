package blackjack.domain.game;

import blackjack.domain.gameresult.Batting;
import blackjack.domain.participant.Player;

import java.util.Map;

public class GameBattings {
    private final Map<Player, Batting> gameBattings;

    public GameBattings(Map<Player, Batting> gameBattings) {
        this.gameBattings = gameBattings;
    }

    public Batting findPlayerBatting(Player player) {
        if (gameBattings.containsKey(player)) {
            return gameBattings.get(player);
        }
        throw new IllegalStateException("조회한 플레이어의 베팅액이 없습니다.");
    }
}
