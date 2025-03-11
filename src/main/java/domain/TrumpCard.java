package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public record TrumpCard(CardShape cardShape, CardNumber cardNumber) {
    private static final List<TrumpCard> CARD_DECK_CACHE = new ArrayList<>();

    static {
        Arrays.stream(CardShape.values())
                .forEach(cardShape -> Arrays.stream(CardNumber.values())
                        .forEach(cardNumber -> CARD_DECK_CACHE.add(new TrumpCard(cardShape, cardNumber))));
        Collections.shuffle(CARD_DECK_CACHE);
    }

    public static List<TrumpCard> cardDeckCaching() {
        return new ArrayList<>(CARD_DECK_CACHE);
    }

    public int getCardNumberValue() {
        return cardNumber.getWorth();
    }
}
