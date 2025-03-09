package domain.game;

import domain.card.CardDeck;

public class Player {

    private final String name;
    private final Hand hand;

    public Player(String name) {
        this.name = name;
        this.hand = new Hand();
    }

    public void drawCardWhenStart(CardDeck cardDeck) {
        hand.drawCardWhenStart(cardDeck);
    }

    public void drawCard(CardDeck cardDeck) {
        hand.drawCard(cardDeck);
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
