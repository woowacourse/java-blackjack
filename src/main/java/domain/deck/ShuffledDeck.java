package domain.deck;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardShape;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ShuffledDeck implements DeckStrategy {

    private final List<Card> cards = new ArrayList<>();

    public ShuffledDeck() {
        for (CardShape cardShape : CardShape.values()) {
            initDeck(cardShape);
        }
        Collections.shuffle(cards);
    }

    private void initDeck(final CardShape cardShape) {
        for (int i = CardNumber.getMinValue(); i <= CardNumber.getMaxValue(); i++) {
            cards.add(Card.of(cardShape, CardNumber.of(i)));
        }
    }

    @Override
    public Card drawCard() {
        try {
            return cards.remove(cards.size() - 1);
        } catch (IndexOutOfBoundsException exception) {
            throw new IllegalStateException("덱에 더이상 카드가 남아있지 않습니다.", exception);
        }
    }

}
