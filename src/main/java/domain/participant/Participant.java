package domain.participant;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import domain.card.Card;

public class Participant {
    private static final int MIN_BUST_NUMBER = 22;

    Name name;
    List<Card> cards;

    public Participant(Name name) {
        this.name = name;
        this.cards = new ArrayList<>();
    }

    public void takeCard(Card card) {
        cards.add(card);
    }

    public int getMaxSum() {
        List<Integer> totalValues = new ArrayList<>();
        totalValues.add(0);
        for (Card card : cards) {
            totalValues = setTotalValuesByAceExistence(totalValues, card);
        }
        return calculateMaxSum(totalValues);
    }

    private Integer calculateMaxSum(List<Integer> totalValues) {
        Integer maxSum = totalValues.stream()
                .filter(value -> value < MIN_BUST_NUMBER)
                .max(Integer::compare)
                .orElseThrow(IllegalStateException::new);
        return maxSum;
    }

    private List<Integer> setTotalValuesByAceExistence(List<Integer> totalValues, Card card) {
        if (card.getValue() == 1) {
            totalValues = getAceSituationValues(totalValues, card);
        }
        if (card.getValue() != 1) {
            setCommonValues(totalValues, card);
        }
        return totalValues;
    }

    private static void setCommonValues(List<Integer> totalValues, Card card) {
        for (int index = 0; index < totalValues.size(); index++) {
            totalValues.set(index, totalValues.get(index) + card.getValue());
        }
    }

    private List<Integer> getAceSituationValues(List<Integer> totalValues, Card card) {
        List<Integer> commonSituationValues = new ArrayList<>();
        List<Integer> aceSituationValues = new ArrayList<>();

        for (int index = 0; index < totalValues.size(); index++) {
            commonSituationValues.add(totalValues.get(index) + card.getValue());
            aceSituationValues.add(totalValues.get(index) + card.getValue() + 10);
        }
        totalValues = Stream.concat(commonSituationValues.stream(), aceSituationValues.stream())
                .collect(Collectors.toList());
        return totalValues;
    }


    public String getName() {
        return name.getRawName();
    }

    public List<String> getCardNames() {
        return cards.stream()
                .map(Card::getName)
                .collect(Collectors.toUnmodifiableList());
    }
}
