package domain;

import java.util.Optional;

public abstract class Participant {
    private final Deck deck;
    private final String name;

    protected Participant(Deck participantDeck, String name) {
        this.deck = participantDeck;
        this.name = name;
    }

    public Deck getDeck() {
        return deck;
    }

    public String getName() {
        return name;
    }

    public boolean isBust() {
        return deck.isBust();
    }

    public int calculateDeckSum() {
        return deck.calculateCardScoreSum();
    }

    public Optional<Card> addCard(Deck totalDeck) {
        Card newCard = totalDeck.drawCard();
        this.deck.addCard(newCard);
        return Optional.of(newCard);
    }
}