package blackjack.domain.result;

import blackjack.domain.participant.BetAmount;
import blackjack.domain.participant.Player;
import java.util.LinkedHashMap;
import java.util.Map;

public class PlayersPots {
    private static final int DEALER_POT_COEFFICIENT = -1;

    private final Map<Player, BetAmount> playersPots;

    public PlayersPots(Map<Player, BetAmount> playersPots) {
        this.playersPots = playersPots;
    }

    public PlayersPots calculatePlayersPots(Map<Player, HandResult> roundResult) {
        Map<Player, BetAmount> calculatedPot = new LinkedHashMap<>();
        for (Player player : roundResult.keySet()) {
            BetAmount initialBetAmount = this.playersPots.get(player);
            HandResult handResult = roundResult.get(player);
            int calculatedBetAmount = (int) (initialBetAmount.amount() * handResult.getRatio());
            calculatedPot.put(player, new BetAmount(calculatedBetAmount));
        }
        return new PlayersPots(calculatedPot);
    }

    public BetAmount calculateDealerBetAmount() {
        return new BetAmount(
                DEALER_POT_COEFFICIENT * playersPots.values().stream()
                .mapToInt(BetAmount::amount)
                .sum()
        );
    }

    public Map<Player, BetAmount> getPlayersPots() {
        return playersPots;
    }

    public BetAmount getBetAmount(Player player) {
        return playersPots.get(player);
    }
}
