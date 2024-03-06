package domain;

public class Dealer {
    private static final Name DEFAULT_NAME = new Name("딜러");

    private final Name name;
    private final Cards cards;
    private final CardDeck cardDeck;

    public Dealer(CardDeck cardDeck) {
        this.name = DEFAULT_NAME;
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
