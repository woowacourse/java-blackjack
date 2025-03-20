package blackjack.object.gambler;

import blackjack.object.ProfitCalculator;
import blackjack.object.card.Card;
import blackjack.object.card.CardType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Gambler {
    private static final int MIN_ACE_VALUE = 1;
    private final List<Card> cards;
    private final Name name;

    public Gambler(final Name name) {
        this.name = name;
        this.cards = new ArrayList<>();
    }

    public void addCard(final Card card) {
        cards.add(card);
    }

    public boolean isNameEquals(final Name name) {
        return Objects.equals(name, this.name);
    }

    public boolean isScoreBelow(final int criteria) {
        return calculateScore() <= criteria;
    }

    public int calculateScore() {
        int sum = calculateSum();
        long aceCount = calculateAceCount();
        if (aceCount == 0) {
            return sum;
        }
        return adjustSumByAce(sum, (int) aceCount);
    }

    private int calculateSum() {
        return cards.stream()
                .mapToInt(Card::getTypeValue)
                .sum();
    }

    private long calculateAceCount() {
        return cards.stream()
                .filter(Card::isAce)
                .count();
    }

    protected int adjustSumByAce(int sum, int aceCount) {
        int minSum = minSum(sum, aceCount);
        if (minSum > ProfitCalculator.BLACK_JACK) {
            return minSum - differenceBetweenMaxAndMinValue();
        }
        return minSum;
    }

    private int minSum(int sum, int aceCount) {
        return sum - differenceBetweenMaxAndMinValue() * (aceCount - 1);
    }

    private int differenceBetweenMaxAndMinValue() {
        return CardType.ACE.getValue() - MIN_ACE_VALUE;
    }

    public Name getName() {
        return name;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public List<Card> getInitialCards() {
        if (name.isDealer()) {
            List<Card> cards = getCards();
            Card firstCard = cards.getFirst();
            return List.of(firstCard);
        }
        return getCards();
    }
}
