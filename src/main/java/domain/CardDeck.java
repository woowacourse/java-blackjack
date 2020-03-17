package domain;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CardDeck {
    private static final String CARD_DECK_IS_EMPTY_EXCEPTION_MESSAGE = "카드덱에 카드가 남아있지 않습니다.";

    private List<Card> cardDeck;

    public CardDeck() {
        cardDeck = new LinkedList<>();
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
        return cardDeck.remove(cardDeck.size() - 1);
    }
}
