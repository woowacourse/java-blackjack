package domain;

import static domain.CardNumberType.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Cards {
    private static final int VALID_MAX_SUM_LIMIT = 21;

    private final List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = cards;
    }

    public static Cards createEmpty() {
        return new Cards(new ArrayList<>());
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

    public void add(Card card) {
        cards.add(card);
    }

    public void addAll(Cards receivedCards) {
        cards.addAll(receivedCards.getCards());
    }

    public int calculateSum() {
        int sum = 0;
        List<Card> sortedCards = getSortedCards();
        for (Card card : sortedCards) {
            if(card.cardNumberType() != ACE) {
                sum += card.getDefaultNumber();
                continue;
            }
            sum += getAceNumber(sum);
        }
        return sum;
    }

    private List<Card> getSortedCards() {
        return cards.stream()
                .sorted(Comparator.comparing(Card::getDefaultNumber).reversed())
                .toList();
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Cards other = (Cards) object;
        return Objects.equals(cards, other.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cards);
    }
}
