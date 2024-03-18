package blackjack.domain.card;

import java.util.List;

public class CardDeck {

    private final List<Card> deck;

    private CardDeck(List<Card> deck) {
        this.deck = deck;
    }

    public static CardDeck of(CardDeckShuffleStrategy cardDeckShuffleStrategy) {
        List<Card> allCards = Card.getAllCards();
        List<Card> shuffledCards = cardDeckShuffleStrategy.shuffle(allCards);

        return new CardDeck(shuffledCards);
    }

    public Card draw() {
        return deck.remove(0);
    }
}
