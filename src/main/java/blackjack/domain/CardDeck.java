package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDeck {
    public static final String NO_CARD_EXCEPTION_MESSAGE = "덱에 남은 카드가 없습니다";
    public static List<Card> cards;

    static {
        cards = new ArrayList<>();
        for (Denomination value : Denomination.values()) {
            for (Symbol symbol : Symbol.values()) {
                cards.add(Card.of(value, symbol));
            }
        }
        Collections.shuffle(cards);
    }

    public static Card giveCard() {
        if (cards.isEmpty()) {
            throw new ArrayIndexOutOfBoundsException(NO_CARD_EXCEPTION_MESSAGE);
        }
        return cards.remove(cards.size() - 1);
    }

    public static List<Card> get() {
        return List.copyOf(cards);
    }
}
