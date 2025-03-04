package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Card {
    private final Rank rank;
    private final Suit suit;
    private static final List<Card> deck = new ArrayList<>();

    static {
        for (Rank rank : Rank.values()) {
            for (Suit suit : Suit.values()) {
                deck.add(new Card(rank, suit));
            }
        }
    }

    private Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public static Card random(NumberGenerator generator) {
        if (deck.isEmpty()) {
            throw new IllegalStateException("[ERROR] 카드를 생성할 수 없습니다.");
        }
        int index = generator.generate(deck.size());
        Card card = deck.get(index);
        deck.remove(index);
        return card;
    }

    public int score() {
        return rank.getValue();
    }

    public boolean isAce() {
        return rank == Rank.ACE;
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
        return rank == card.rank && suit == card.suit;
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(rank);
        result = 31 * result + Objects.hashCode(suit);
        return result;
    }
}
