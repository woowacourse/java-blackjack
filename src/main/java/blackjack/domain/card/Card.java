package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Card {
    private final Denomination denomination;
    private final Suit suit;

    public Card(Denomination denomination, Suit suit) {
        this.denomination = denomination;
        this.suit = suit;
    }

    public static List<Card> values() {
        return Collections.unmodifiableList(CardCache.CARD_CACHE);
    }

    public boolean isAce() {
        return denomination.isAce();
    }

    public String getCardInformation() {
        return denomination.getDenomination() + suit;
    }

    public int getScore() {
        return denomination.getScore();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return denomination == card.denomination &&
                Objects.equals(suit, card.suit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, denomination);
    }

    private static class CardCache {
        static final List<Card> CARD_CACHE = new ArrayList<>();

        static {
            CARD_CACHE.addAll(generateCards());
        }

        static List<Card> generateCards() {
            return Stream.of(Denomination.values())
                    .flatMap(CardCache::generateCardsBySymbol)
                    .collect(Collectors.toList());
        }

        static Stream<Card> generateCardsBySymbol(Denomination denomination) {
            return Stream.of(Suit.values())
                    .map(suit -> new Card(denomination, suit));
        }
    }
}
