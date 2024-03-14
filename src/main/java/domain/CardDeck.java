package domain;

import java.util.List;

public class CardDeck {

    private final List<Card> deck;

    private CardDeck(List<Card> deck) {
        this.deck = deck;
    }

    public static CardDeck of(CardShuffleStrategy cardShuffleStrategy) {
        List<Card> allCards = Card.getAllCards();
        List<Card> shuffledCards = cardShuffleStrategy.shuffle(allCards);

        return new CardDeck(shuffledCards);
    }

    public Card draw() {
        return deck.remove(0);
    }
}
