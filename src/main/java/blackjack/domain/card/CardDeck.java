package blackjack.domain.card;

import blackjack.constants.ErrorCode;
import blackjack.domain.card.exception.NoMoreCardException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDeck {
    private final List<Card> cards;

    public CardDeck() {
        this.cards = new ArrayList<>(Card.values());
        shuffleCards();
    }

    public CardDeck(List<Card> cards) {
        this.cards = cards;
    }

    private void shuffleCards() {
        Collections.shuffle(this.cards);
    }

    public Card pick() {
        if (cards.isEmpty()) {
            throw new NoMoreCardException(ErrorCode.EMPTY_CARD);
        }
        return cards.remove(0);
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}
