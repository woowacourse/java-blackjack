package blackjack.domain.card;

import java.util.*;

public class Card {

    private static final List<Card> ORIGINAL_CARDS = new ArrayList<>();
    static {
        for(Suit suit : Suit.values()){
            Arrays.stream(Denomination.values())
                    .forEach(denomination -> ORIGINAL_CARDS.add(new Card(suit, denomination)));
        }
    }

    private final Suit suit;
    private final Denomination denomination;

    private Card(final Suit suit, final Denomination denomination){
        this.suit = suit;
        this.denomination = denomination;
    }

    public static Card of(final Suit suit, final Denomination denomination) {
        return ORIGINAL_CARDS.stream()
                .filter(card -> card.equals(new Card(suit, denomination)))
                .findFirst()
                .orElseThrow(()-> new IllegalArgumentException("유효하지 않은 카드입니다."));
    }

    public static LinkedList<Card> getCachingCards(){
        return new LinkedList<>(ORIGINAL_CARDS);
    }

    public Denomination getDenomination() {
        return denomination;
    }

    public Suit getSuit() {
        return suit;
    }

    public boolean isAce(){
        return denomination == Denomination.ACE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return denomination == card.denomination && Objects.equals(suit, card.suit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, denomination);
    }
}
