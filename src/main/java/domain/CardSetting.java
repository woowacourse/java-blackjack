package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardSetting {
    private static final List<TrumpCard> CARD_DECK = new ArrayList<>();

    public static void initCache() {
        for (CardShape cardShape : CardShape.values()) {
            for (CardNumber cardNumber : CardNumber.values()) {
                CARD_DECK.add(new TrumpCard(cardShape, cardNumber));
            }
        }
        Collections.shuffle(CARD_DECK);
    }

    public static void bin() {
        while (!CARD_DECK.isEmpty()) {
            CARD_DECK.removeFirst();
        }
    }

    public static TrumpCard drawCard() {
        if (CARD_DECK.isEmpty()) {
            throw new IllegalArgumentException("카드가 다 떨어졌습니다");
        }
        return CARD_DECK.removeFirst();
    }

    public static TrumpCard getCard(int index) {
        return CARD_DECK.get(index);
    }

    public static List<TrumpCard> getCardDeck() {
        return CARD_DECK;
    }

    public int size() {
        return 0;
    }
}
