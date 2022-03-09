package domain.card;

import java.util.*;

public class CardDistributor {
    private static final List<Card> CACHE = new ArrayList<>();
    private final Stack<Card> deck = new Stack<>();

    static {
        for (Suit suit : Suit.values()) {
            for (Denomination denomination : Denomination.values()) {
                CACHE.add(new Card(denomination, suit));
            }
        }
    }

    public CardDistributor() {
        Collections.shuffle(CACHE);
        deck.addAll(CACHE);
    }

    public Card distribute() {
        if (deck == null || deck.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 카드가 모두 소요됐습니다.");
        }
        return deck.pop();
    }
}
