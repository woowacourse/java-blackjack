package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class Deck {
    private static final String NO_CARD_ERROR_MESSAGE = "더 이상 뽑을 수 있는 카드가 없습니다.";

    private final LinkedList<Card> cards;

    public Deck() {
        cards = createCards();
    }

    private LinkedList<Card> createCards() {
        LinkedList<Card> cards = new LinkedList<>();
        for (Suit suit : Suit.values()) {
            createSuitDenominations(cards, suit);
        }
        Collections.shuffle(cards);
        return cards;
    }

    private void createSuitDenominations(List<Card> cards, Suit suit) {
        for (Denomination denomination : Denomination.values()) {
            cards.add(new Card(denomination, suit));
        }
    }

    public Card draw() {
        try {
            return cards.remove();
        } catch (NoSuchElementException e) {
            throw new IllegalStateException(NO_CARD_ERROR_MESSAGE);
        }
    }
}
