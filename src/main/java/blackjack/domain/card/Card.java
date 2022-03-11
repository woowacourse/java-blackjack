package blackjack.domain.card;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Card {

    private static final String NOT_EXIST_CARD_ERROR = "존재하지 않는 카드입니다.";
    private static final List<Card> CARDS;

    private final CardShape cardShape;
    private final CardNumber cardNumber;

    static {
        CARDS = Arrays.stream(CardShape.values())
                .flatMap(shape -> Arrays.stream(CardNumber.values())
                        .map(number -> new Card(shape, number)))
                .collect(Collectors.toList());
    }

    private Card(CardShape cardShape, CardNumber cardNumber) {
        this.cardShape = cardShape;
        this.cardNumber = cardNumber;
    }

    public static Card getInstance(CardShape shape, CardNumber number) {
        return CARDS.stream()
                .filter(card -> card.isSame(shape, number))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(NOT_EXIST_CARD_ERROR));
    }

    private boolean isSame(CardShape shape, CardNumber number) {
        return cardShape == shape && cardNumber == number;
    }

    public boolean isAce() {
        return cardNumber.equals(CardNumber.ACE);
    }

    public static List<Card> getCards() {
        return new LinkedList<>(CARDS);
    }

    public int getValue() {
        return cardNumber.getValue();
    }

    public String getName() {
        return cardNumber.getName();
    }

    public String getShape() {
        return cardShape.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Card card = (Card) o;
        return cardShape == card.cardShape && cardNumber == card.cardNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardShape, cardNumber);
    }
}
