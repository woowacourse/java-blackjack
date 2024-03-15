package blackjack.domain.result;

import blackjack.domain.participant.BetAmount;
import blackjack.domain.participant.Player;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Pot {
    private final Map<Player, BetAmount> pot;

    public Pot(Map<Player, BetAmount> pot) {
        this.pot = pot;
    }

    public BetAmount getBetAmount(Player player) {
        return pot.get(player);
    }

    public Pot calculatePot(RoundResult roundResult) {
        Map<Player, BetAmount> calculatedPot = new LinkedHashMap<>();
        Map<Player, HandResult> playersResult = roundResult.getPlayersResult();
        for (Player player : playersResult.keySet()) {
            HandResult handResult = playersResult.get(player);
            BetAmount initialBetAmount = this.pot.get(player);
            String calculatedBetAmount = String.valueOf((int) (initialBetAmount.getAmount() * handResult.getRatio()));
            calculatedPot.put(player, new BetAmount(calculatedBetAmount));
        }
        return new Pot(calculatedPot);
    }
}
