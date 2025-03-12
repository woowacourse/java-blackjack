package domain.card;

import static domain.card.CardNumberType.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Hand {
    private static final int VALID_MAX_SUM_LIMIT = 21;

    private final List<Card> cards;

    public Hand(List<Card> cards) {
        this.cards = cards;
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
        int sumWithLowAce = cards.stream()
                .map(Card::cardNumberType)
                .mapToInt(CardNumberType::getDefaultNumber)
                .sum();
        return sumWithLowAce > VALID_MAX_SUM_LIMIT;
    }

    public void add(Card receivedCard) {
        cards.add(receivedCard);
    }

    public void addAll(Hand receivedHand) {
        cards.addAll(receivedHand.getCards());
    }

    public int calculateSum() {
        int nonAceSum = calculateSumWithoutAce();
        return calculateSumContainingAce(nonAceSum);
    }

    private int calculateSumWithoutAce() {
        int sum = 0;
        List<Card> nonAceCards = cards.stream()
                .filter(card -> card.cardNumberType() != ACE)
                .toList();

        for (Card card : nonAceCards) {
            sum += card.getDefaultNumber();
        }
        return sum;
    }

    private int calculateSumContainingAce(int restSum) {
        int sum = restSum;
        int aceCount = (int) cards.stream()
                .filter(card -> card.cardNumberType() == ACE)
                .count();

        for (int i = 0; i < aceCount; i++) {
            sum += getAceNumber(sum);
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
