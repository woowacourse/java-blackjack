package blackJack.domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Card {

    private static final String ERROR_MESSAGE_INVALID_CARD = "카드가 존재하지 않습니다.";

    private static final List<Card> CARDS;

    static {
        CARDS = Arrays.stream(Suit.values())
                .flatMap(suit -> Arrays.stream(Denomination.values())
                        .map(denomination -> new Card(suit, denomination)))
                .collect(Collectors.toUnmodifiableList());
    }

    private final Suit suit;
    private final Denomination denomination;

    private Card(Suit suit, Denomination denomination) {
        this.suit = suit;
        this.denomination = denomination;
    }

    public static Card from(Suit suit, Denomination denomination) {
        return CARDS.stream()
                .filter(card -> card.suit == suit && card.denomination == denomination)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ERROR_MESSAGE_INVALID_CARD));
    }

    public static List<Card> initializeDeck() {
        return new ArrayList<>(CARDS);
    }

    public boolean isAce() {
        return denomination == Denomination.ACE;
    }

    public String getSymbolName() {
        return suit.getName();
    }

    public String getDenominationName() {
        return denomination.getDenomination();
    }

    public int getScore() {
        return denomination.getScore();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Card))
            return false;
        Card card = (Card) o;
        return suit == card.suit && denomination == card.denomination;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, denomination);
    }
}
