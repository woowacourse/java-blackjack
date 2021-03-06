package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    public static final int CARD_MAX_COUNT = 52;
    private final List<Card> cards = new ArrayList<>();

    public Deck() {
        setShuffledNewCards();
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            setShuffledNewCards();
        }
        return cards.remove(0);
    }


    private void setShuffledNewCards() {
        for (int cardCount = 1; cardCount <= CARD_MAX_COUNT; ++cardCount) {
            cards.add(Card.of());
        }
        Collections.shuffle(cards);
    }
}
