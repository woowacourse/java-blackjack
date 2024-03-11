package domain.blackjack;

import domain.participant.Participant;

import java.util.LinkedHashMap;

public class BetAmount {

    private final LinkedHashMap<Participant, Integer> bet;

    public BetAmount(LinkedHashMap<Participant, Integer> bet) {
        this.bet = bet;
    }

    public double getPayout(Participant participant, WinStatus winStatus) {
        return bet.get(participant) * winStatus.getBetMultiplier();
    }
}
