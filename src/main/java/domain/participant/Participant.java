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

    public int selectNotBustCardValueSum() {
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
            List<Integer> sumValueWithoutAce = addValueToTotalValues(totalValues, handValue);
            totalValues.clear();
            totalValues.addAll(sumValueWithoutAce);
        }
    }

    private void addAceValueToTotalValues(List<Integer> totalValues, Integer handValue) {
        if (handValue == Denomination.ACE_VALUE) {
            List<Integer> aceValueSum = addValueToTotalValues(totalValues, Denomination.ACE_VALUE);
            List<Integer> anotherAceValueSum =  addValueToTotalValues(totalValues, Denomination.ANOTHER_ACE_VALUE);
            totalValues.clear();
            totalValues.addAll(aceValueSum);
            totalValues.addAll(anotherAceValueSum);
        }
    }

    private List<Integer> addValueToTotalValues(List<Integer> totalValues, int addValue) {
        return totalValues.stream()
                .map(value -> value + addValue)
                .collect(Collectors.toList());
    }

    private int getCardValueSumMax(List<Integer> totalValues, List<Integer> handCardValues) {
        int maxSumValue = 0;
        if (handCardValues.contains(Denomination.ACE_VALUE)) {
            maxSumValue = selectNotBustCardValueSum(totalValues);
        }
        if (!handCardValues.contains(Denomination.ACE_VALUE)) {
            maxSumValue = selectMaxCardValueSum(totalValues);
        }
        return maxSumValue;
    }

    private Integer selectNotBustCardValueSum(List<Integer> totalValues) {
        return totalValues.stream()
                .filter(value -> value < MIN_BUST_VALUE)
                .max(Integer::compare)
                .orElse(MIN_BUST_VALUE);
    }

    private int selectMaxCardValueSum(List<Integer> totalValues) {
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
