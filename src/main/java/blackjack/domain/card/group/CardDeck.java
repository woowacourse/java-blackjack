package blackjack.domain.card.group;

import blackjack.domain.card.Card;
import blackjack.domain.card.element.Denomination;
import blackjack.domain.card.element.Suit;
import java.util.Collections;
import java.util.Stack;

public final class CardDeck {
    private static final String NO_CARD_EXCEPTION_MESSAGE = "덱에 남은 카드가 없습니다";
    
    private final Stack<Card> value;
    
    private CardDeck() {
        this.value = new Stack<>();
        for (Denomination value : Denomination.values()) {
            initFromDenomination(value);
        }
        Collections.shuffle(value);
    }
    
    public static CardDeck newInstance() {
        return new CardDeck();
    }
    
    public Card pop() {
        validateSize();
        return value.pop();
    }
    
    private void initFromDenomination(Denomination denomination) {
        for (Suit suit : Suit.values()) {
            this.value.push(Card.of(denomination, suit));
        }
    }
    
    private void validateSize() {
        if (value.isEmpty()) {
            throw new ArrayIndexOutOfBoundsException(NO_CARD_EXCEPTION_MESSAGE);
        }
    }
}
