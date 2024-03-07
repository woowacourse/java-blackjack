package domain.participants;


import domain.deck.Card;
import domain.deck.Deck;

public class Player {

    private final Name name;
    private final Deck deck;

    public Player(Name name) {
        this.name = name;
        this.deck = new Deck();
    }

    public void receiveDeck(Deck tempDeck) {
        int tempDeckSize = tempDeck.size();
        for (int i = 0; i < tempDeckSize; i++) {
            deck.addCard(tempDeck.pickRandomCard());
        }
    }

    public void receiveCard(Card card) {
        deck.addCard(card);
    }

    public int calculateScore() {
        return deck.calculateTotalScore();
    }

    public boolean isNotOver(int boundaryScore) {
        return deck.calculateTotalScore() < boundaryScore;
    }

    public Name getName() {
        return name;
    }

    public Deck getDeck() {
        return deck;
    }
}
