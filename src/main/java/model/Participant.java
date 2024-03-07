package model;

public abstract class Participant {

    private final Name name;
    final CardDeck cardDeck;

    protected Participant(Name name, CardDeck cardDeck) {
        this.name = name;
        this.cardDeck = cardDeck;
    }

    public abstract boolean canHit();

    public void hitCard(Card card) {
        cardDeck.addCard(card);
    }
}
