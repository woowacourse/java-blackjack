package model;

public class CardDispenser {

    private final Cards deck;

    public CardDispenser(Cards deck) {
        this.deck = deck.shuffle();
    }

    public void dispense(Player player, int count) {
        for (int i = 0; i < count; i++) {
            player.addCard(deck.pickCard());
        }
    }
}
