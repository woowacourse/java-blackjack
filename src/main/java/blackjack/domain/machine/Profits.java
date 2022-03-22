package blackjack.domain.machine;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import java.util.LinkedHashMap;
import java.util.Map;

public class Profits {
    private final Map<Participant, Profit> result;

    private Profits(Map<Participant, Profit> result) {
        this.result = result;
    }

    public static Profits of(Dealer dealer, Players players) {
        Map<Participant, Profit> result = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            result.put(player, Profit.of(dealer, player));
        }

        result.put(dealer, Profit.getDealerProfit(result));
        return new Profits(result);
    }

    public Map<Participant, Profit> getResult() {
        return result;
    }
}
