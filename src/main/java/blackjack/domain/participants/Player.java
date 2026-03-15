package blackjack.domain.participants;

import blackjack.domain.card.Hand;

public class Player extends Participant {
    private final Bet betAmount;

    public Player(Name name, Hand hand, Bet bet) {
        super(name, hand);
        this.betAmount = bet;
    }

    public Player(String name, Hand hand, Bet bet) {
        super(new Name(name), hand);
        this.betAmount = bet;
    }

    @Override
    public boolean canHit() {
        return !isBust();
    }
}
