package domain;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CardDeck {

    private static final String CARD_DECK_IS_EMPTY_EXCEPTION_MESSAGE = "카드덱에 카드가 남아있지 않습니다.";
    private static List<Card> cardDeck;

    static {
        cardDeck = new LinkedList<>();
        for (Type type : Type.values()) {
            for (Symbol symbol : Symbol.values()) {
                cardDeck.add(new Card(type, symbol));
            }
        }
        Collections.shuffle(cardDeck);
    }

    public static Card draw() {
        if (cardDeck.size() == 0) {
            throw new IndexOutOfBoundsException(CARD_DECK_IS_EMPTY_EXCEPTION_MESSAGE);
        }
        return cardDeck.remove(cardDeck.size() - 1);
    }
}
