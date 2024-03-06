package blackjack.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class Referee {
    private final Dealer dealer;
    private final Players players;

    public Referee(final Dealer dealer, final Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public Map<String, Result> judgePlayerWinner() {
        Map<String, Result> result = new LinkedHashMap<>();
        for (Player player: players.getPlayers()) {
            result.put(player.getName(), calculateResult(player.notifyScore()));
        }
        return result;
    }

    private Result calculateResult(final int playerScore) {
        if (playerScore > dealer.notifyScore()) {
            return Result.WIN;
        }
        return Result.LOSE;
    }
}
