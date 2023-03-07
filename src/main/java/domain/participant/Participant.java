package domain.participant;

import domain.card.Card;
import domain.card.HandCards;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Participant {
    public static final int MIN_BUST_NUMBER = 21;

    private final Name name;
    private final HandCards handCards;

    public Participant(Name name) {
        this.name = name;
        this.handCards = new HandCards();
    }

    private Integer calculateMaxSum(List<Integer> totalValues) {
        return totalValues.stream()
                .filter(value -> value <= MIN_BUST_NUMBER)
                .max(Integer::compare)
                .orElse(totalValues.get(0));
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

        for (Integer totalValue : totalValues) {
            commonSituationValues.add(totalValue + card.getValue());
            aceSituationValues.add(totalValue + card.getValue() + 10);
        }
        totalValues = Stream.concat(commonSituationValues.stream(), aceSituationValues.stream())
                .collect(Collectors.toList());
        return totalValues;
    }

    public int getMaxSum() {
        List<Integer> totalValues = new ArrayList<>();
        totalValues.add(0);
        for (Card card : handCards.getCards()) {
            totalValues = setTotalValuesByAceExistence(totalValues, card);
        }
        return calculateMaxSum(totalValues);
    }

    public String getName() {
        return name.getValue();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(handCards.getCards());
    }

    public List<String> getCardNames() {
        return handCards.getCards().stream()
                .map(Card::getName)
                .collect(Collectors.toUnmodifiableList());
    }

    public void drawCard(Card card) {
        handCards.takeCard(card);
    }
}
