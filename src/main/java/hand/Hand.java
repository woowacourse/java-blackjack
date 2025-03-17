package hand;

import card.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import participant.value.Score;
import result.GameStatus;

public abstract class Hand {
    protected static final int INITIAL_HAND_SIZE = 2;

    protected final List<Card> cards;

    public Hand(List<Card> cards) {
        this.cards = cards;
    }

    public static Hand createEmpty() {
        return new RegularHand(new ArrayList<>());
    }

    public static Hand from(Card card) {
        List<Card> newCards = new ArrayList<>();
        newCards.add(card);
        return new RegularHand(newCards);
    }

    public static Hand create(List<Card> cards) {
        RegularHand regularHand = new RegularHand(cards);
        if (regularHand.isBlackjack()) {
            return new BlackjackHand(cards);
        }
        Score score = Score.from(cards);
        if (score.isBust()) {
            return new BustHand(cards);
        }
        return regularHand;
    }

    public abstract GameStatus calculateResultAgainst(Hand other);

    public abstract boolean isBust();

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public Card getFirst() {
        return cards.getFirst();
    }

    public Hand add(Card card) {
        List<Card> newCards = new ArrayList<>(cards);
        newCards.add(card);
        return Hand.create(newCards);
    }

    public boolean isBlackjack() {
        return Score.from(cards).isBlackJackValue() && cards.size() == INITIAL_HAND_SIZE;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Hand hand = (Hand) o;
        return Objects.equals(cards, hand.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cards);
    }
}
