package model.participant;

import model.deck.Deck;

public class Player extends Participant {
    private final String name;

    public Player(final String name,final Deck deck) {
        super();
        this.name = name;
        drawInitialCards(deck);
    }

    private void drawInitialCards(final Deck deck) {
        receiveCard(deck.getCard());
        receiveCard(deck.getCard());
    }

    @Override
    protected boolean canDrawMore() {
        return isNotBust();
    }

    public String getName() {
        return name;
    }
}
