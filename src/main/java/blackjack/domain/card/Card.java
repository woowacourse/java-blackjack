package blackjack.domain.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Card {
    private final Face face;
    private final Suit suit;

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
        Card card = cached.get(0);
        cached.remove(0);
        return card;
    }

    public boolean isAce() {
        return this.face.equals(Face.ACE);
    }

    public int getFaceValue() {
        return this.face.getValue();
    }

    public String getSuit() {
        return this.suit.getSuit();
    }
}
