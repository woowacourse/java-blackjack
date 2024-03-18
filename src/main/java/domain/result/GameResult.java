package domain.result;

import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Players;
import domain.vo.Profit;

import java.util.LinkedHashMap;
import java.util.Map;

public class GameResult {

    private final Map<Participant, Profit> result;

    private GameResult(final Map<Participant, Profit> result) {
        this.result = new LinkedHashMap<>(result);
    }

    public static GameResult export(final Dealer dealer, final Players players) {
        Map<Participant, Profit> result = new LinkedHashMap<>();
        result.put(dealer, dealer.calculateProfit(players));
        players.forEach(player -> result.put(player, player.profit()));
        return new GameResult(result);
    }

    public Map<Participant, Profit> getResult() {
        return new LinkedHashMap<>(result);
    }
}
