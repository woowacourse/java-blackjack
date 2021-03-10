package blackjack.domain.card;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Card {

    private static final List<Card> cached;

    private final Face face;
    private final Suit suit;

    static {
        cached = Arrays.stream(Suit.values())
                .flatMap(suit -> Arrays.stream(Face.values()).map(face -> new Card(suit, face)))
                .collect(Collectors.toList());
    }

    public Card(final Suit suit, final Face face) {
        this.suit = suit;
        this.face = face;
    }

    public static Card of() {
        Card card = cached.remove(0);
        cached.add(card);
        return card;
    }

    public int getFaceValueAsInt() {
        return this.face.getValue();
    }

    public String getSuitAsString() {
        return this.suit.getSuit();
    }

    public boolean isAce() {
        return this.face.equals(Face.ACE);
    }
}
