package model.participant;

import model.card.Cards;

public class Player extends Participant {
    private Player(String name) {
        super(name);
    }

    public static Participant of(String input) {
        return new Player(input);
    }

    @Override
    public Cards open() {
        return Cards.from(hands);
    }
}
