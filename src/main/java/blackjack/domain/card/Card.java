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
    private final CardNumber cardNumber;

    private Card(Suit suit, CardNumber cardNumber) {
        this.suit = suit;
        this.cardNumber = cardNumber;
    }

    public static List<Card> createDeck() {
        return new ArrayList<>(CACHE_CARDS);
    }

    public static Card valueOf(Suit suit, CardNumber cardNumber) {
        return CACHE_CARDS.stream()
                .filter(card -> card.containSuit(suit) && card.containNumber(cardNumber))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카드입니다."));
    }

    public CardNumber getNumber() {
        return this.cardNumber;
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
        return suit == card.suit && cardNumber == card.cardNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, cardNumber);
    }

    private static Stream<Card> toCard(Suit suit) {
        return Arrays.stream(CardNumber.values())
                .map(number -> new Card(suit, number));
    }

    private boolean containNumber(CardNumber cardNumber) {
        return this.cardNumber == cardNumber;
    }

    private boolean containSuit(Suit suit) {
        return this.suit == suit;
    }
}
