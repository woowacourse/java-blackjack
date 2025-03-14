package card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Hand {
    private static final int INITIAL_HAND_SIZE = 2;

    private final List<Card> cards;

    public Hand(List<Card> cards) {
        this.cards = cards;
    }

    public Hand(Card card) {
        this.cards = new ArrayList<>();
        this.cards.add(card);
    }

    public static Hand createEmpty() {
        return new Hand(new ArrayList<>());
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public Card getFirst() {
        return cards.getFirst();
    }

    public void add(Card receivedCard) {
        cards.add(receivedCard);
    }

    public void addAll(Hand receivedHand) {
        cards.addAll(receivedHand.getCards());
    }

    public boolean isInitialStatus() {
        return cards.size() == INITIAL_HAND_SIZE;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Hand other = (Hand) object;
        return Objects.equals(cards, other.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cards);
    }
}
