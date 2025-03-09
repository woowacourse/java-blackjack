package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TrumpCardManager {
    private static final List<TrumpCard> CARD_DECK = new ArrayList<>();

    public static void initCache() {
        Arrays.stream(CardShape.values())
                .forEach(cardShape -> Arrays.stream(CardNumber.values())
                        .forEach(cardNumber -> CARD_DECK.add(new TrumpCard(cardShape, cardNumber))));
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

    public static List<TrumpCard> getCardDeck() {
        return CARD_DECK;
    }
}
