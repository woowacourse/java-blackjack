package model;

public class CardDispenser {

    private static final int START_CARD_NUMBER = 2;
    private final Cards deck;

    public CardDispenser(Cards deck) {
        this.deck = deck;
    }

    public void dispenseOneCard(Player player) {
        player.addCard(deck.pickCard());
    }

    public void dispenseStartingCards(Player player) {
        for (int i = 0; i < START_CARD_NUMBER; i++) {
            player.addCard(deck.pickCard());
        }
    }
}
