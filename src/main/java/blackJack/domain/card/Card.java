package blackJack.domain.card;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Card {

    private static final List<Card> CARDS = List.copyOf(initializeDeck());

    private final Suit suit;
    private final Denomination denomination;

    public Card(Suit suit, Denomination denomination) {
        this.suit = suit;
        this.denomination = denomination;
    }

    public static List<Card> initializeDeck() {
        return Arrays.stream(Suit.values())
                .flatMap(suit -> Arrays.stream(Denomination.values())
                        .map(denomination -> new Card(suit, denomination)))
                .collect(Collectors.toCollection(LinkedList::new));
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
