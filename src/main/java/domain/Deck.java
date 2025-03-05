package domain;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    private final List<Card> deck = new ArrayList<>();

    public Deck() {
        for (Rank rank : Rank.values()) {
            for (Suit suit : Suit.values()) {
                deck.add(new Card(rank, suit));
            }
        }
    }

    public Card random(NumberGenerator generator) {
        if (deck.isEmpty()) {
            throw new IllegalStateException("[ERROR] 카드를 생성할 수 없습니다.");
        }
        int index = generator.generate(deck.size());
        Card card = deck.get(index);
        deck.remove(index);
        return card;
    }
}
