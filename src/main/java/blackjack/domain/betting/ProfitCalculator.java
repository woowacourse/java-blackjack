package blackjack.domain.betting;

import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import java.util.LinkedHashMap;
import java.util.Map;

public class ProfitCalculator {

    private final Participants participants;
    private final Map<Player, Long> playerProfit = new LinkedHashMap<>();

    public ProfitCalculator(Participants participants) {
        this.participants = participants;
    }

    public void calculate() {
        for (Player player : participants.getPlayers()) {
            playerProfit.put(player, player.getProfit(participants.getDealer()));
        }
    }

    public Map<Player, Long> getPlayerProfit() {
        return playerProfit;
    }

    public long calculateDealerProfit() {
        return -playerProfit.values()
            .stream()
            .mapToLong(profit -> profit)
            .sum();
    }

}
