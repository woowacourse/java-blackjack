package blackJack.domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Card {

    private static final Set<Card> CARDS;

    static {
        CARDS = Arrays.stream(Suit.values())
                .flatMap(suit -> Arrays.stream(Denomination.values())
                        .map(denomination -> new Card(suit, denomination)))
                .collect(Collectors.toSet());
    }

    private final Suit suit;
    private final Denomination denomination;

    private Card(Suit suit, Denomination denomination) {
        this.suit = suit;
        this.denomination = denomination;
    }

    public static List<Card> valuesOf() {
        return new ArrayList<>(CARDS);
    }

    public static Card valueOf(Suit suit, Denomination denomination) {
        return CARDS.stream()
                .filter(card -> card.suit == suit && card.denomination == denomination)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카드입니다."));
    }

    public boolean isSameDenominationAsAce() {
        return denomination == Denomination.ACE;
    }

    public String getSymbolName() {
        return suit.getName();
    }

    public int getScore() {
        return denomination.getScore();
    }

    public String getDenominationName() {
        return denomination.getDenomination();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Card)) {
            return false;
        }
        Card card = (Card) o;
        return suit == card.suit && denomination == card.denomination;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, denomination);
    }
}
