package domain;

public class Deck {
    private final Cards cards;

    public Deck() {
        this.cards = new Cards();

        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }

        cards.shuffle();
    }
}
