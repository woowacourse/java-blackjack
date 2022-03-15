package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Card {

    private final CardNumber cardNumber;
    private final CardShape cardShape;

    private static final List<Card> CACHE = new ArrayList<>();

    static {
        for (CardNumber cardNumber : CardNumber.values()) {
            selectCardShape(cardNumber);
        }
    }

    private static void selectCardShape(final CardNumber cardNumber) {
        for (CardShape cardShape : CardShape.values()) {
            CACHE.add(new Card(cardNumber, cardShape));
        }
    }

    private Card(final CardNumber cardNumber, final CardShape cardShape) {
        this.cardNumber = cardNumber;
        this.cardShape = cardShape;
    }

    public static Card of(final CardNumber cardNumber, final CardShape cardShape) {
        return new Card(cardNumber, cardShape);
    }

    public CardNumber getCardNumber() {
        return this.cardNumber;
    }

    public String getName() {
        return cardNumber.getName() + cardShape.getShape();
    }

    public static List<Card> getDeck() {
        return Collections.unmodifiableList(CACHE);
    }
}
