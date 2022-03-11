package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Card {
    private static final List<Card> CACHE_CARDS;

    static {
        CACHE_CARDS = Arrays.stream(Suit.values())
                .flatMap(Card::toCard)
                .collect(Collectors.toList());
    }

    private static Stream<Card> toCard(Suit suit) {
        return Arrays.stream(Number.values())
                .map(number -> new Card(suit, number));
    }

    private final Suit suit;
    private final Number number;

    private Card(Suit suit, Number number) {
        this.suit = suit;
        this.number = number;
    }

    public static List<Card> createDeck() {
        return new ArrayList<>(CACHE_CARDS);
    }

    public static Card valueOf(Suit suit, Number number) {
        return CACHE_CARDS.stream()
                .filter(card -> card.containSuit(suit) && card.containNumber(number))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카드입니다."));
    }

    private boolean containNumber(Number number) {
        return this.number == number;
    }

    private boolean containSuit(Suit suit) {
        return this.suit == suit;
    }

    public Number getNumber() {
        return this.number;
    }

    public Suit getSuit() {
        return suit;
    }
}
