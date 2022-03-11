package blackjack.domain.card;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CardFactory {

    private static final int FIRST_CARD = 0;
    private static final String EMPTY_CARD_ERROR = "남은 카드가 존재하지 않습니다.";

    private final List<Card> deck;

    public CardFactory(List<Card> deck) {
        this.deck = new LinkedList<>(deck);
        Collections.shuffle(this.deck);
    }

    public Card draw() {
        validateEmptyDeck();
        Card card = deck.get(FIRST_CARD);
        deck.remove(card);
        return card;
    }

    private void validateEmptyDeck() {
        if (deck.isEmpty()) {
            throw new IllegalStateException(EMPTY_CARD_ERROR);
        }
    }

    public int getSize() {
        return deck.size();
    }
}
