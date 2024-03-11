package domain.blackjack;

import domain.participant.Participant;

import java.util.LinkedHashMap;
import java.util.Set;

public class BettingResult {

    private final LinkedHashMap<Participant, BetAmount> bet;
    private final BlackJackResult blackJackResult;

    public BettingResult(LinkedHashMap<Participant, BetAmount> bet, BlackJackResult blackJackResult) {
        this.bet = bet;
        this.blackJackResult = blackJackResult;
    }

    public double getPayout(Participant participant) {
        return bet.get(participant).getBetAmount() * blackJackResult.getWinStatus(participant).getBetMultiplier();
    }

    public double getDealerPayout() {
        double total = 0;
        for (Participant participant : bet.keySet()) {
            total += getPayout(participant);
        }
        return total * -1;
    }

    public Set<Participant> getParticipants() {
        return bet.keySet();
    }
}
