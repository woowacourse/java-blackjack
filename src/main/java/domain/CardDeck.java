package domain;

import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDeck {
    private static final List<TrumpCard> CARD_DECK = new ArrayList<>();

    static {
        for (CardShape cardShape : CardShape.values()) {
            for (CardNumber cardNumber : CardNumber.values()) {
                CARD_DECK.add(new TrumpCard(cardShape, cardNumber));
            }
        }
        Collections.shuffle(CARD_DECK);
    }

    public static TrumpCard drawCard() {
        if (CARD_DECK.isEmpty()) {
            throw new IllegalArgumentException("카드가 다 떨어졌습니다");
        }
        return CARD_DECK.remove(0);
    }

    public static TrumpCard getCard(int index) {
        return CARD_DECK.get(index);
    }

    public static List<TrumpCard> getCardDeck() {
        return CARD_DECK;
    }
}
