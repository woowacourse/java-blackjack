package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Card {
    private static final List<Card> CACHE_CARDS;

    static {
        CACHE_CARDS = Arrays.stream(Suit.values())
                .flatMap(Card::toCard)
                .collect(Collectors.toList());
    }

    private final Suit suit;
    private final Denomination denomination;

    private Card(Suit suit, Denomination denomination) {
        this.suit = suit;
        this.denomination = denomination;
    }

    public static List<Card> createDeck() {
        return new ArrayList<>(CACHE_CARDS);
    }

    public static Card valueOf(Suit suit, Denomination denomination) {
        return CACHE_CARDS.stream()
                .filter(card -> card.containSuit(suit) && card.containDenomination(denomination))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카드입니다."));
    }

    public Denomination getDenomination() {
        return this.denomination;
    }

    public Suit getSuit() {
        return suit;
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

    private static Stream<Card> toCard(Suit suit) {
        return Arrays.stream(Denomination.values())
                .map(denomination -> new Card(suit, denomination));
    }

    private boolean containDenomination(Denomination denomination) {
        return this.denomination == denomination;
    }

    private boolean containSuit(Suit suit) {
        return this.suit == suit;
    }
}
