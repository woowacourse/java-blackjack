package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    public static final int CARD_MAX_COUNT = 52;
    private final List<Card> cards;
    public int usedCardCount = 0;

    {
        cards = new ArrayList<>();
        for (int cardCount = 0; cardCount < CARD_MAX_COUNT; ++cardCount) {
            cards.add(Card.of(cardCount));
        }
    }

    public Deck() {
        shuffleCards();
    }

    public Card draw() {
        if (usedCardCount == CARD_MAX_COUNT) {
            shuffleCards();
            usedCardCount = 0;
        }
        return cards.get(usedCardCount++);
    }

    private void shuffleCards() {
        Collections.shuffle(cards);
    }
}
