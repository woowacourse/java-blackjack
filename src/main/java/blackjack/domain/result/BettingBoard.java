package blackjack.domain.result;

import blackjack.domain.player.PlayerName;
import java.util.HashMap;
import java.util.Map;

public class BettingBoard {
    private final Map<PlayerName, Money> board = new HashMap<>();

    public void bet(PlayerName playerName, Money money) {
        board.put(playerName, money);
    }

    public Money findByPlayerName(PlayerName playerName) {
        return board.get(playerName);
    }
}
