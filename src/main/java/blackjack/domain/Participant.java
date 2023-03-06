package blackjack.domain;

abstract public class Participant {

    private final CardDeck cardDeck;
    private final Name name;

    public Participant(String name) {
        this.name = new Name(name);
        cardDeck = new CardDeck();
    }

    public void hit(Card card) {
        cardDeck.addCard(card);
    }

    public int calculateScore() {
        return cardDeck.calculateScore(cardDeck);
    }

    public Name getName() {
        return name;
    }

    public CardDeck getCardDeck() {
        return cardDeck;
    }
}
