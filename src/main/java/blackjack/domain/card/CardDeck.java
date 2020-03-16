package blackjack.domain.card;

import java.util.Collections;
import java.util.LinkedList;

public class CardDeck {

    private static final String CARD_DECK_IS_EMPTY_EXCEPTION_MESSAGE
        = "카드덱에 카드가 남아있지 않습니다.";

    private final LinkedList<Card> cardDeck = new LinkedList<>();

    public CardDeck() {
        for (Type type : Type.values()) {
            for (Symbol symbol : Symbol.values()) {
                cardDeck.add(new Card(type, symbol));
            }
        }
        Collections.shuffle(cardDeck);
    }

    public Card draw() {
        if (cardDeck.isEmpty()) {
            throw new IndexOutOfBoundsException(CARD_DECK_IS_EMPTY_EXCEPTION_MESSAGE);
        }
        return cardDeck.poll();
    }
}
