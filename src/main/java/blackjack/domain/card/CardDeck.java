package blackjack.domain.card;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CardDeck {
    private static final int CARD_NUMBER = 52;

    private final List<Card> cards;

    public CardDeck(final List<Card> cards) {
        validateCards(cards);
        this.cards = new LinkedList<>(cards);
    }

    private void validateCards(final List<Card> cards) {
        if (cards.size() != CARD_NUMBER) {
            throw new IllegalStateException("블랙잭을 진행하기 위해서는 52장의 카드가 필요합니다.");
        }
    }

    public Card distribute() {
        return cards.remove(0);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
