package blackjack.domain;

import blackjack.domain.card.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDeck {
    private final List<Card> cards;
    private int index;

    public CardDeck(List<Card> cards) {
        this.cards = shuffle(cards);
        this.index = 0;
    }

    private List<Card> shuffle(final List<Card> cards) {
        List<Card> shuffledCards = new ArrayList<>(cards);
        Collections.shuffle(shuffledCards);
        return shuffledCards;
    }

    public Card getCard() {
        return cards.get(index++);
    }
}
