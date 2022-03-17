package blackjack.domain.card;

import java.util.Stack;

public class CardDeck {

    private static final String NO_CARD_ERROR_MESSAGE = "[ERROR] 남은 카드가 존재하지 않습니다.";

    private Stack<Card> cards;

    private CardDeck(Stack<Card> cards) {
        this.cards = cards;
    }

    public static CardDeck generate(Stack<Card> cards) {
        return new CardDeck(cards);
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new ArrayIndexOutOfBoundsException(NO_CARD_ERROR_MESSAGE);
        }
        return cards.pop();
    }
}
