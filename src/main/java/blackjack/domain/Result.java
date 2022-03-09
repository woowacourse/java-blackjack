package blackjack.domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Result {
    private final Participant dealer;
    private final List<Participant> players;

    public Result(Participant dealer, List<Participant> players) {
        this.dealer = dealer;
        this.players = players;
    }

    public Map<Participant, Boolean> getResult() {
        Map<Participant, Boolean> result = new LinkedHashMap<>();
        players.forEach(player -> result.put(player, isWin(player)));
        return result;
    }

    private boolean isWin(Participant player) {
        return dealer.getScore() < player.getScore();
    }
}
