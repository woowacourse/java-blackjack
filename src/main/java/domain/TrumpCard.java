package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public record TrumpCard(CardShape cardShape, CardRank cardNumber) {
    private static final List<TrumpCard> CARD_DECK_CACHE;

    static {
        CARD_DECK_CACHE = Arrays.stream(CardShape.values())
                .flatMap(cardShape -> Arrays.stream(CardRank.values())
                        .map(cardRank -> new TrumpCard(cardShape, cardRank))
                ).collect(Collectors.toList());

        Collections.shuffle(CARD_DECK_CACHE);
    }

    public static List<TrumpCard> cardDeckCaching() {
        return new ArrayList<>(CARD_DECK_CACHE);
    }

    public int getCardNumberValue() {
        return cardNumber.getPoint();
    }
}
