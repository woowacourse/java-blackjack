import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Dealer {
    private static final int LIMIT_TAKE_CARD_VALUE = 17;
    public static final String DEALER_NAME = "딜러";
    private static final int MIN_BUST_NUMBER = 22;
    private final String name;
    private final List<Card> cards;

    public Dealer() {
        this.name = DEALER_NAME;
        this.cards = new ArrayList<>();
    }

    public void takeCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public boolean checkCardsCondition() {
        List<Integer> totalValues = new ArrayList<>();
        totalValues.add(0);
        for (Card card : cards) {
            totalValues = setTotalValuesByAceExistence(totalValues, card);
        }
        Integer maxSum = calculateMaxSum(totalValues);
        return maxSum < LIMIT_TAKE_CARD_VALUE;
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
        if (card.getValue() != 1){
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
}
