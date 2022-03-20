package blackjack.domain.dto;

import blackjack.domain.outcome.Outcome;
import blackjack.domain.user.Player;
import java.util.Map;

public class ResponseOutcomeDto {

    private final Map<Outcome, Integer> dealerOutcome;
    private final Map<Player, Outcome> playerOutcomes;

    public ResponseOutcomeDto(Map<Outcome, Integer> dealerOutcome, Map<Player, Outcome> playerOutcomes) {
        this.dealerOutcome = dealerOutcome;
        this.playerOutcomes = playerOutcomes;
    }

    public Map<Outcome, Integer> getDealerOutcome() {
        return dealerOutcome;
    }

    public Map<Player, Outcome> getPlayerOutcomes() {
        return playerOutcomes;
    }
}
