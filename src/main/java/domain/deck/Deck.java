package domain.deck;

import domain.card.Card;
import domain.card.CardFactory;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Deck {
    private static final int DECKS_COUNT = 6;
    private static final String NO_CARDS = "카드가 모두 소진되었습니다.";
    private final Stack<Card> cards;

    public Deck(CardFactory cardFactory) {
        this.cards = cardFactory.createCards(DECKS_COUNT);
    }

    public Card draw() {
        if (cards.empty()) {
            throw new IllegalArgumentException(NO_CARDS);
        }
        return cards.pop();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
