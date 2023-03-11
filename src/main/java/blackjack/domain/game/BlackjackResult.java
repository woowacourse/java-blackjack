package blackjack.domain.game;

import blackjack.domain.player.Player;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class BlackjackResult {
    private final Map<Player, GameResult> blackjackResult;

    public BlackjackResult(Map<Player, GameResult> blackjackResult) {
        this.blackjackResult = blackjackResult;
    }

    public int getTieCount() {
        return Collections.frequency(blackjackResult.values(), GameResult.TIE);
    }

    public int getDealerWinCount() {
        return Collections.frequency(blackjackResult.values(), GameResult.LOSE);
    }

    public int getDealerLoseCount() {
        return Collections.frequency(blackjackResult.values(), GameResult.WIN);
    }

    public List<Player> getPlayer() {
        return List.copyOf(blackjackResult.keySet());
    }

    public GameResult get(Player player) {
        return blackjackResult.get(player);
    }
}
