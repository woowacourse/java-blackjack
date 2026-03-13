package domain.participant;

import domain.result.MatchCase;

public class Player extends Participant {
    private final Bet bet;

    public Player(String name, Bet bet) {
        super(name);
        this.bet = bet;
    }

    public long calculateProfit(MatchCase matchCase) {
        return Math.round(bet.amount() * matchCase.getBenefitRate());
    }
}
