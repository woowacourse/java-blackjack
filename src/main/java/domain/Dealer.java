package domain;

public class Dealer {
    private final Name name;
    private final Cards cards;
    private final CardDeck cardDeck;

    public Dealer(String name, CardDeck cardDeck) {
        this.name = new Name("딜러");
        this.cards = new Cards();
        this.cardDeck = new CardDeck();
    }

    public Card pickCard() {
        return cardDeck.draw();
    }

    public void add(Card card) {
        cards.addCard(card);
    }

    public int calculateScore() {
        return cards.sumAll();
    }
}
