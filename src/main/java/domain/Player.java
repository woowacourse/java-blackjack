package domain;

public class Player {
    private final Cards cards;

    public Player() {
        cards = Cards.empty();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public Cards getCards() {
        return cards;
    }
}
