package blackjack.domain.player;

import blackjack.domain.card.Card;

public class Participant extends Player{

    private Participant(String name, Card first, Card second) {
        super(name, first, second);
    }

    public static Participant of(String name, Card first, Card second) {
        return new Participant(name,first,second);
    }

    @Override
    public boolean drawable() {
        return this.state().drawable();
    }
}
