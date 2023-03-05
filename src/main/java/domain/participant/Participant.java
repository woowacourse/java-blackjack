package domain.participant;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain.card.Card;

public abstract class Participant {
    private static final int ACE_VALUE = 1;
    private static final int ANOTHER_ACE_VALUE = 11;
    private static final int MIN_BUST_VALUE = 22;

    protected Name name;
    protected HandCards handCards;

    public Participant(Name name, HandCards handCards) {
        this.name = name;
        this.handCards = handCards;
    }

    public void takeCard(Card card) {
        handCards.addCard(card);
    }

    public int getOptimalSumValue() {
        List<Integer> totalValues = new ArrayList<>();
        totalValues.add(0);
        for (Integer handValue : handCards.getValues()) {
            addNotAceValueToTotalValues(totalValues, handValue);
            addAceValueToTotalValues(totalValues, handValue);
        }
        return getTotalValueMaxNoBust(totalValues);
    }

    private int getTotalValueMaxNoBust(List<Integer> totalValues) {
        return totalValues.stream()
                .filter(totalValue -> totalValue < MIN_BUST_VALUE)
                .max(Integer::compare)
                .orElse(MIN_BUST_VALUE);
    }

    private void addNotAceValueToTotalValues(List<Integer> totalValues, Integer handValue) {
        if (handValue != ACE_VALUE) {
            List<Integer> sumValueWithoutAce = totalValues.stream()
                    .map(value -> value + handValue)
                    .collect(Collectors.toList());
            totalValues.clear();
            totalValues.addAll(sumValueWithoutAce);
        }
    }
    
    private void addAceValueToTotalValues(List<Integer> totalValues, Integer handValue) {
        if (handValue == ACE_VALUE) {
            List<Integer> aceValueSum = getAceValueSum(totalValues);
            List<Integer> anotherAceValueSum = getAnotherAceValueSum(totalValues);
            totalValues.clear();
            totalValues.addAll(aceValueSum);
            totalValues.addAll(anotherAceValueSum);
        }
    }

    private List<Integer> getAceValueSum(List<Integer> totalValues) {
        return totalValues.stream()
                .map(value -> value + ACE_VALUE)
                .filter(value -> value < MIN_BUST_VALUE)
                .collect(Collectors.toList());
    }

    private List<Integer> getAnotherAceValueSum(List<Integer> totalValues) {
        return totalValues.stream()
                .map(value -> value + ANOTHER_ACE_VALUE)
                .filter(value -> value < MIN_BUST_VALUE)
                .collect(Collectors.toList());
    }

    public String getName() {
        return name.getValue();
    }

    public List<Card> getHandCards() {
        return handCards.getCards();
    }
}
