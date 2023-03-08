package domain.participant;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain.card.Card;
import domain.card.Denomination;

public abstract class Participant {

    private static final int MIN_BUST_VALUE = 22;
    private static final int INIT_VALUE_FOR_ADD = 0;

    protected Name name;
    protected HandCards handCards;

    public Participant(Name name, HandCards handCards) {
        this.name = name;
        this.handCards = handCards;
    }

    public void takeCard(Card card) {
        handCards.addCard(card);
    }

    public int getCardValueSum() {
        List<Integer> totalValues = new ArrayList<>();
        totalValues.add(INIT_VALUE_FOR_ADD);
        List<Integer> handCardValues = handCards.getValues();
        for (Integer handValue : handCardValues) {
            addNotAceValueToTotalValues(totalValues, handValue);
            addAceValueToTotalValues(totalValues, handValue);
        }
        return getCardValueSumMax(totalValues, handCardValues);
    }

    private void addNotAceValueToTotalValues(List<Integer> totalValues, Integer handValue) {
        if (handValue != Denomination.ACE_VALUE) {
            List<Integer> sumValueWithoutAce = totalValues.stream()
                    .map(value -> value + handValue)
                    .collect(Collectors.toList());
            totalValues.clear();
            totalValues.addAll(sumValueWithoutAce);
        }
    }

    private void addAceValueToTotalValues(List<Integer> totalValues, Integer handValue) {
        if (handValue == Denomination.ACE_VALUE) {
            List<Integer> aceValueSum = getAceValueSum(totalValues);
            List<Integer> anotherAceValueSum = getAnotherAceValueSum(totalValues);
            totalValues.clear();
            totalValues.addAll(aceValueSum);
            totalValues.addAll(anotherAceValueSum);
        }
    }

    private List<Integer> getAceValueSum(List<Integer> totalValues) {
        return totalValues.stream()
                .map(value -> value + Denomination.ACE_VALUE)
                .filter(value -> value < MIN_BUST_VALUE)
                .collect(Collectors.toList());
    }

    private List<Integer> getAnotherAceValueSum(List<Integer> totalValues) {
        return totalValues.stream()
                .map(value -> value + Denomination.ANOTHER_ACE_VALUE)
                .filter(value -> value < MIN_BUST_VALUE)
                .collect(Collectors.toList());
    }

    private int getCardValueSumMax(List<Integer> totalValues, List<Integer> handCardValues) {
        int maxSumValue = 0;
        if (handCardValues.contains(Denomination.ACE_VALUE)) {
            maxSumValue = getCardValueSum(totalValues);
        }
        if (!handCardValues.contains(Denomination.ACE_VALUE)) {
            maxSumValue = getJustSumValue(totalValues);
        }
        return maxSumValue;
    }

    private Integer getCardValueSum(List<Integer> totalValues) {
        return totalValues.stream()
                .filter(value -> value < MIN_BUST_VALUE)
                .max(Integer::compare)
                .orElse(MIN_BUST_VALUE);
    }

    private int getJustSumValue(List<Integer> totalValues) {
        int optimalSumValue;
        optimalSumValue = totalValues.stream()
                .max(Integer::compare)
                .orElse(MIN_BUST_VALUE);
        return optimalSumValue;
    }

    public String getName() {
        return name.getValue();
    }

    public List<Card> getHandCards() {
        return handCards.getCards();
    }
}
