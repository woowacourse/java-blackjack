package domain.card;

import java.util.*;

public class Deck {

    private final List<Card> cards = new ArrayList<>();

    public Deck(ShuffleStrategy shuffleStrategy) {
        for (Suit suit : Suit.values()) {
            generateRank(suit);
        }

        shuffleStrategy.shuffle(cards);
    }

    public Card drawCard() {
        if (isAvailable()) {
            return cards.removeFirst();
        }

        throw new IllegalArgumentException("더 이상 카드가 존재하지 않습니다.");
    }

    private void generateRank(Suit suit) {
        for (Rank rank : Rank.values()) {
            cards.add(new Card(rank, suit));
        }
    }

    private boolean isAvailable() {
        return !cards.isEmpty();
    }
}
