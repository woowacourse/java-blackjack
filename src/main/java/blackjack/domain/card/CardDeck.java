package blackjack.domain.card;

import blackjack.domain.card.cardElement.Denomination;
import blackjack.domain.card.cardElement.Suit;
import java.util.Collections;
import java.util.Stack;

public class CardDeck {
    private static final String NO_CARD_EXCEPTION_MESSAGE = "덱에 남은 카드가 없습니다";
    private final Stack<Card> value;
    
    private CardDeck() {
        this.value = new Stack<>();
        for (Denomination value : Denomination.values()) {
            initForDenomination(value);
        }
        Collections.shuffle(value);
    }
    
    public static CardDeck getInstance() {
        return new CardDeck();
    }
    
    private void initForDenomination(Denomination denomination) {
        for (Suit suit : Suit.values()) {
            this.value.push(Card.of(denomination, suit));
        }
    }
    
    public int size() {
        return value.size();
    }
    
    public Card draw() {
        validateSize();
        return value.pop();
    }
    
    private void validateSize() {
        if (value.isEmpty()) {
            throw new ArrayIndexOutOfBoundsException(NO_CARD_EXCEPTION_MESSAGE);
        }
    }
}
