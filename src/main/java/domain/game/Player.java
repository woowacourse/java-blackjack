package domain.game;

import domain.card.CardDeck;

public class Player {

    private final String name;
    private final Hand hand;

    public Player(String name, CardDeck cardDeck) {
        this.name = name;
        this.hand = new Hand(cardDeck);
    }

    public void drawCard(int cardCount) {
        hand.drawCard(cardCount);
    }

    public int getCardsCount() {
        return hand.getCardsCount();
    }

    public int calculateTotalCardNumber() {
        return hand.calculateTotalWithAce();
    }

    public boolean isOverBustBound() {
        return hand.isOverBustBound();
    }

    public Hand getHand() {
        return hand;
    }

    public String getName() {
        return name;
    }
}
