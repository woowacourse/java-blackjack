package model.participant;

import java.util.List;
import model.deck.Card;

public final class Player extends Participant {
    private final String name;

    public Player(final String name) {
        super();
        this.name = name;
    }

    @Override
    public List<Card> openInitialDeal() {
        return participantHand.getCards();
    }

    @Override
    public boolean canHit() {
        return !isBurst();
    }

    public String getName() {
        return name;
    }
}
