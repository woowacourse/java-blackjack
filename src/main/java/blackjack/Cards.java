package blackjack;

import java.util.*;

public class Cards {

    private static final Set<Card> originalCards;

    static {
        originalCards = new HashSet<>();
        for (Suit suit : Suit.values()) {
            for (Number number : Number.values()) {
                if (number != Number.HIDDEN_ACE) {
                    originalCards.add(new Card(suit, number));
                }
            }
        }
    }

    private final Queue<Card> cards;

    private Cards(Queue<Card> cards) {
        this.cards = cards;
    }

    public static Cards create() {
        List<Card> cards = new ArrayList<>(originalCards);
        Collections.shuffle(cards);
        return new Cards(new LinkedList<>(cards));
    }

    public Card draw() {
        return cards.poll();
    }
}
