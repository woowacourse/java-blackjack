package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    private final List<Card> cards = new ArrayList<>();
    private final NumberGenerator numberGenerator;

    public Deck(NumberGenerator numberGenerator) {
        generateDeck();
        this.numberGenerator = numberGenerator;
    }

    private void generateDeck() {
        for (Suit suit : Suit.values()) {
            generateCardEachSuit(suit);
        }
    }

    private void generateCardEachSuit(Suit suit) {
        for (Letter letter : Letter.values()) {
            cards.add(new Card(suit, letter));
        }
    }

    public Card getCard() {
        return cards.remove(numberGenerator.generate(cards.size()));
    }
}
