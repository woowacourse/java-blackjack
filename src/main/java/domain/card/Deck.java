package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private final List<Card> cards = new ArrayList<>();

    public Deck() {
        for (Suit suit : Suit.values()) {
            generateRank(suit);
        }

        Collections.shuffle(cards);
    }

    public Card drawCard() {
        if (!cards.isEmpty()) {
            return cards.removeFirst();
        }

        throw new IllegalArgumentException("더 이상 카드가 존재하지 않습니다.");
    }

    private void generateRank(Suit suit) {
        for (Rank rank : Rank.values()) {
            cards.add(new Card(rank, suit));
        }
    }
}
