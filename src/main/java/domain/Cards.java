package domain;

import static domain.CardNumberType.*;

import java.util.ArrayList;
import java.util.Collections;
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

    public boolean isLargerThan(Cards dealerCards) {
        return this.calculateSum() > dealerCards.calculateSum();
    }

    public int calculateSum() {
        int cardsSumWithoutAce = calculateSumWithoutAce();
        return calculateSumWithAces(cardsSumWithoutAce);
    }

    public int calculateSumWithoutAce() {
        return cards.stream()
                .filter(Card::isNotAce)
                .mapToInt(Card::getDefaultNumber)
                .sum();
    }

    private int calculateSumWithAces(int cardsSumWithoutAce) {
        int result = cardsSumWithoutAce;
        int aceCount = calculateAceCount();
        for (int count = 0; count < aceCount; count++) {
            result += determineAceNumber(result);
        }
        return result;
    }

    private int calculateAceCount() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
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
