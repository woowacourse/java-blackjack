package blackjack.domain.card;

import java.util.*;

public class Card {

    private static final Map<Suit, Map<Denomination, Card>> cache = new HashMap<>();

    static {
        List<Suit> suits = Suit.getAll();
        for (Suit suit : suits) {
            cache.put(suit, createAllNumberCard(suit));
        }
    }

    private final Suit suit;
    private final Denomination denomination;

    private Card(Suit suit, Denomination denomination) {
        this.suit = suit;
        this.denomination = denomination;
    }

    public static Card of(Suit suit, Denomination denomination) {
        return cache.get(suit).get(denomination);
    }

    public static List<Card> getAllCards() {
        List<Card> cards = new ArrayList<>();

        for (Suit suit : Suit.values()) {
            addCards(cards, suit);
        }

        return cards;
    }

    private static void addCards(List<Card> cards, Suit suit) {
        for (Denomination denomination : Denomination.values()) {
            cards.add(Card.of(suit, denomination));
        }
    }

    private static Map<Denomination, Card> createAllNumberCard(Suit suit) {
        List<Denomination> denominations = Denomination.getAll();
        Map<Denomination, Card> numberCard = new HashMap<>();
        for (Denomination denomination : denominations) {
            numberCard.put(denomination, new Card(suit, denomination));
        }
        return numberCard;
    }

    public Suit getSuit() {
        return suit;
    }

    public Denomination getDenomination() {
        return denomination;
    }

    @Override
    public String toString() {
        return "Card{" +
                "suit=" + suit.name()+
                ", denomination=" + denomination.name() +
                '}';
    }
}
