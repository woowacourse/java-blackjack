package model;

public class Dealer {

    private static final int ADD_CARD_CONDITION = 17;

    private final Cards cards;

    public Dealer(Cards cards) {
        this.cards = cards;
    }

    public boolean isPossibleAddCard() {
        int totalNumbers = cards.calculateTotalNumbers();
        return totalNumbers < ADD_CARD_CONDITION;
    }

    public Dealer addCard(Card card) {
        if (isPossibleAddCard()) {
            Cards addedCards = cards.add(card);
            return new Dealer(addedCards);
        }
        return this;
    }

    public int cardSize() {
        return cards.size();
    }
}
