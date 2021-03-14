package blackjack.domain.player;

import blackjack.domain.card.Card;

public class Participant extends Player {

    private Participant(String name, int batMoney, Card first, Card second) {
        super(name, batMoney, first, second);
    }

    public static Participant of(String name, int batMoney, Card first, Card second) {
        return new Participant(name, batMoney, first, second);
    }

    @Override
    public boolean drawable() {
        return this.state().drawable();
    }
}
