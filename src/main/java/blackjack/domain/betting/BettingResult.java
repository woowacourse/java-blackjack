package blackjack.domain.betting;

import blackjack.domain.participant.Participant;

public class BettingResult {

    private final Participant participant;
    private final int moneyOutcome;

    public BettingResult(Participant participant, int moneyOutcome) {
        this.participant = participant;
        this.moneyOutcome = moneyOutcome;
    }

    public Participant getParticipant() {
        return participant;
    }

    public int getMoneyOutcome() {
        return moneyOutcome;
    }

    @Override
    public String toString() {
        return "BettingResult{" +
                "participant=" + participant +
                ", moneyOutcome=" + moneyOutcome +
                '}';
    }
}
