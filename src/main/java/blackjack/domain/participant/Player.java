package blackjack.domain.participant;

import blackjack.domain.Bet;
import blackjack.domain.Hand;
import blackjack.domain.Name;

public class Player extends Participant{
    private Bet bet;

    private Player(Name name, Hand hand, Bet bet) {
        super(name, hand);
        this.bet = bet;
    }

    public static Player of(Name name) {
        return new Player(name, Hand.init(), Bet.init());
    }

    public void placeBet(int amount) {
        this.bet = bet.add(amount);
    }

    public Bet getBet() {
        return bet;
    }

    @Override
    public boolean canHit() {
        return !isBust();
    }
}

