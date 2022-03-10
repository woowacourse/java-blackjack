package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDeck {
    private static final String NO_CARD_EXCEPTION_MESSAGE = "덱에 남은 카드가 없습니다";
    private final List<Card> cards;
    {
        cards = new ArrayList<>();
        for (Denomination value : Denomination.values()) {
            for (Symbol symbol : Symbol.values()) {
                cards.add(Card.of(value, symbol));
            }
        }
        Collections.shuffle(cards);
    }

    private CardDeck() {
    }

    public static CardDeck getInstance() {
        return new CardDeck();
    }

    public Card giveCard() {
        if (cards.isEmpty()) {
            throw new ArrayIndexOutOfBoundsException(NO_CARD_EXCEPTION_MESSAGE);
        }
        return cards.remove(cards.size() - 1);
    }

    public List<Card> get() {
        return List.copyOf(cards);
    }
}
