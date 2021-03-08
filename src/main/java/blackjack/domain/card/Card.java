package blackjack.domain.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Card {
    private final Face face;
    private final Suit suit;

    private static int currentCardCount = 52;
    private static int totalCardCount = 52;
    private static final List<Card> cached;

    static {
        cached = Arrays.stream(Suit.values())
                .flatMap(suit -> Arrays.stream(Face.values()).map(face -> new Card(suit, face)))
                .collect(Collectors.toList());
        Collections.shuffle(cached);
    }

    public Card(final Suit suit, final Face face) {
        this.suit = suit;
        this.face = face;
    }

    public static Card of() {
        Card card = cached.remove(0);
        cached.add(card);
        currentCardCount --;

        if(currentCardCount == 0){
            Collections.shuffle(cached);
            currentCardCount = totalCardCount;
        }
        return card;
    }

    public int getFaceValue() {
        return this.face.getValue();
    }

    public String getSuit() {
        return this.suit.getSuit();
    }

    public boolean isAce() {
        return this.face.equals(Face.ACE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return face == card.face && suit == card.suit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(face, suit);
    }
}
