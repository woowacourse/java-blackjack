package domain.blackjack;

import domain.participant.Participant;

import java.util.LinkedHashMap;

public class BetAmount {

    private final LinkedHashMap<Participant, Integer> bet;
    private final BlackJackResult blackJackResult;

    public BetAmount(LinkedHashMap<Participant, Integer> bet, BlackJackResult blackJackResult) {
        this.bet = bet;
        this.blackJackResult = blackJackResult;
    }

    public double getPayout(Participant participant) {
        return bet.get(participant) * blackJackResult.getWinStatus(participant).getBetMultiplier();
    }

    public double getDealerPayout() {
        double total = 0;
        for (Participant participant : bet.keySet()) {
            total += getPayout(participant);
        }
        return total * -1;
    }
}
