package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Card {

    private static final List<Card> CARDS_CACHE = createCards();

    private final Suit suit;
    private final Denomination denomination;

    private Card(final Suit suit, final Denomination denomination) {
        this.suit = suit;
        this.denomination = denomination;
    }

    private static List<Card> createCards() {
        return Arrays.stream(Suit.values())
                .map(Card::createSuitCards)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    private static List<Card> createSuitCards(final Suit suit) {
        return Arrays.stream(Denomination.values())
                .map(denomination -> new Card(suit, denomination))
                .collect(Collectors.toList());
    }

    public static Card of(final Suit suit, final Denomination denomination) {
        return CARDS_CACHE.stream()
                .filter(card -> isEqualCard(card, suit, denomination))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카드입니다."));
    }

    private static boolean isEqualCard(final Card card, final Suit suit, final Denomination denomination) {
        return card.suit == suit && card.denomination == denomination;
    }

    public static List<Card> cards() {
        return new ArrayList<>(createCards());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Card card = (Card) o;
        return suit == card.suit && denomination == card.denomination;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, denomination);
    }
}
