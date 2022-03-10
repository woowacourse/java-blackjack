package blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardGenerator {

    private final List<Card> deck;
    private int index;

    public CardGenerator() {
        deck = new ArrayList<>();
        index = 0;
        for (Rank rank : Rank.values()) {
            for (Suit suit : Suit.values()) {
                deck.add(new Card(rank, suit));
            }
        }
        Collections.shuffle(deck);
    }

    public Card generate() {
        if (index >= deck.size()) {
            throw new IllegalStateException("남아있는 카드가 없습니다.");
        }

        return deck.get(index++);
    }
}
