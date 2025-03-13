package domain.card;

import static domain.card.CardNumberType.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Hand {
    private static final int VALID_MAX_SUM_LIMIT = 21;
    private static final int ACE_ADDING_VALUE = 10;
    private static final int ACE_VALUE_ADDING_AVAILABLE_SUM = 11;

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

    public boolean isBust() {
        return calculateSum() > VALID_MAX_SUM_LIMIT;
    }

    public void add(Card receivedCard) {
        cards.add(receivedCard);
    }

    public void addAll(Hand receivedHand) {
        cards.addAll(receivedHand.getCards());
    }

    public int calculateSum() {
        int defaultSum = calculateDefaultSum();
        return addHighAceNumberToDefaultSum(defaultSum);
    }

    private int calculateDefaultSum() {
        return cards.stream()
                .mapToInt(Card::getNumber)
                .sum();
    }

    private int addHighAceNumberToDefaultSum(int sum) {
        boolean hasAce = cards.stream()
                .anyMatch(card -> card.cardNumberType() == ACE);
        if(hasAce && sum <= ACE_VALUE_ADDING_AVAILABLE_SUM) {
            sum += ACE_ADDING_VALUE;
        }
        return sum;
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
