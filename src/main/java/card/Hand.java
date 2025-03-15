package card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public record Hand(List<Card> cards) {
    private static final int INITIAL_HAND_SIZE = 2;

    public static Hand createEmpty() {
        return new Hand(new ArrayList<>());
    }

    public static Hand from(Card card) {
        List<Card> newCards =  new ArrayList<>();
        newCards.add(card);
        return new Hand(newCards);
    }

    public Hand add(Card card) {
        List<Card> newCards = new ArrayList<>(cards);
        newCards.add(card);
        return new Hand(newCards);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public Card getFirst() {
        return cards.getFirst();
    }

    public boolean isInitialStatus() {
        return cards.size() == INITIAL_HAND_SIZE;
    }
}
