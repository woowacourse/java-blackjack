package model.participant;

import model.bettingamount.BettingAmount;
import model.deck.Deck;

public class Player extends Participant {
    private final String name;
    private final BettingAmount bettingAmount;

    public Player(final String name, final Deck deck, final BettingAmount bettingAmount) {
        super();
        this.name = name;
        this.bettingAmount = bettingAmount;
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

    public BettingAmount getBettingAmount() {
        return bettingAmount;
    }
}
