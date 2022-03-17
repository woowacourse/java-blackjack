package blackjack.domain.cards;

import blackjack.domain.cards.card.Card;
import blackjack.domain.cards.card.denomination.Denomination;
import blackjack.domain.cards.card.denomination.Suit;
import java.util.Collections;
import java.util.Stack;

public final class CardDeck {
    private static final String NO_CARD_EXCEPTION_MESSAGE = "덱에 남은 카드가 없습니다";

    private final Stack<Card> value;

    private CardDeck() {
        this.value = new Stack<>();
        init();
        Collections.shuffle(value);
    }

    public static CardDeck newInstance() {
        return new CardDeck();
    }

    private void init() {
        for (Denomination value : Denomination.values()) {
            initFromDenomination(value);
        }
    }

    private void initFromDenomination(Denomination denomination) {
        for (Suit suit : Suit.values()) {
            this.value.push(Card.of(denomination, suit));
        }
    }

    public Card pop() {
        validateSize();
        return value.pop();
    }

    private void validateSize() {
        if (value.isEmpty()) {
            throw new ArrayIndexOutOfBoundsException(NO_CARD_EXCEPTION_MESSAGE);
        }
    }
}
