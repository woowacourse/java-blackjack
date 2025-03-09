package model.participant;

import model.deck.Deck;

public class Player extends Participant {
    private final String name;

    public Player(String name, Deck deck) {
        super();
        this.name = name;
        drawInitialCards(deck);
    }

    private void drawInitialCards(Deck deck) {
        receiveCard(deck.getCard());
        receiveCard(deck.getCard());
    }

    @Override
    protected boolean canDrawMore() {
        return !isBust();
    }

    public String getName() {
        return name;
    }
}
