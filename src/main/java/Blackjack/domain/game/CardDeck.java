package Blackjack.domain.game;

import Blackjack.domain.card.Card;
import Blackjack.exception.ErrorException;
import java.util.ArrayList;
import java.util.List;

public class CardDeck {

    private static final int DECK_SIZE = 52;

    private final List<Card> cards;

    public CardDeck(final List<Card> cards) {
        validateSize(cards);
        this.cards = new ArrayList<>(cards);
    }

    public Card pickCard() {
        return cards.removeFirst();
    }

    public List<Card> pickCards(final int size) {
        final List<Card> cards = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            cards.add(pickCard());
        }
        return cards;
    }

    private void validateSize(final List<Card> cards) {
        if (cards.size() != DECK_SIZE) {
            throw new ErrorException("카드는 총 " + DECK_SIZE + "장이어야 합니다.");
        }
    }
}
