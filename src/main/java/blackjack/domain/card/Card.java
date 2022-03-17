package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Card {

    private static final List<Card> TOTAL_CARD_CACHE = new ArrayList<>();

    private final Denomination denomination;
    private final CardShape cardShape;

    static {
        for (Denomination denomination : Denomination.values()) {
            insertCardCache(denomination);
        }
    }

    private static void insertCardCache(final Denomination denomination) {
        for (CardShape cardShape : CardShape.values()) {
            TOTAL_CARD_CACHE.add(new Card(denomination, cardShape));
        }
    }

    private Card(final Denomination denomination, final CardShape cardShape) {
        this.denomination = denomination;
        this.cardShape = cardShape;
    }

    public static Card of(final Denomination denomination, final CardShape cardShape) {
        return TOTAL_CARD_CACHE.stream()
                .filter(card -> card.equals(new Card(denomination, cardShape)))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("적절하지 않은 카드 정보가 존재합니다."));
    }

    public static List<Card> getTotalCard() {
        return Collections.unmodifiableList(TOTAL_CARD_CACHE);
    }

    public Denomination getCardNumber() {
        return this.denomination;
    }

    public String getName() {
        return denomination.getName() + cardShape.getShape();
    }

    @Override
    public boolean equals(final Object otherCard) {
        if (this == otherCard) {
            return true;
        }
        if (!(otherCard instanceof Card)) {
            return false;
        }
        Card card = (Card) otherCard;
        return denomination == card.denomination && cardShape == card.cardShape;
    }

    @Override
    public int hashCode() {
        return Objects.hash(denomination, cardShape);
    }
}
