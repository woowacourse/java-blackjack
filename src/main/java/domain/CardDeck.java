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

    public static TrumpCard getCard(int index) {
        return CARD_DECK.get(index);
    }
}
