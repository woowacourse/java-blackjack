package blackjack.domain.result;

import blackjack.domain.participant.BetAmount;
import blackjack.domain.participant.Player;
import java.util.LinkedHashMap;
import java.util.Map;

public class Pot {
    private static final int DEALER_POT_COEFFICIENT = -1;

    private final Map<Player, BetAmount> pot;

    public Pot(Map<Player, BetAmount> pot) {
        this.pot = pot;
    }

    public Pot calculatePlayerPot(RoundResult roundResult) {
        Map<Player, BetAmount> calculatedPot = new LinkedHashMap<>();
        Map<Player, HandResult> playersResult = roundResult.playersResult();
        for (Player player : playersResult.keySet()) {
            BetAmount initialBetAmount = this.pot.get(player);
            HandResult handResult = playersResult.get(player);
            int calculatedBetAmount = (int) (initialBetAmount.amount() * handResult.getRatio());
            calculatedPot.put(player, new BetAmount(calculatedBetAmount));
        }
        return new Pot(calculatedPot);
    }

    public BetAmount calculateDealerBetAmount() {
        return new BetAmount(
                DEALER_POT_COEFFICIENT * pot.values().stream()
                .mapToInt(BetAmount::amount)
                .sum()
        );
    }

    public Map<Player, BetAmount> getPot() {
        return pot;
    }

    public BetAmount getBetAmount(Player player) {
        return pot.get(player);
    }
}
