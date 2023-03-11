package domain.participant;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain.card.Card;
import domain.card.Denomination;
import domain.score.TotalSumValues;

public class Hand {

    private static final int INIT_VALUE_FOR_ADD = 0;
    private static final int BLACKJACK_VALUE = 21;
    private static final int BLACKJACK_SIZE = 2;
    private static final int MIN_BUST_VALUE = 22;

    private final List<Card> cards;

    public Hand(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int calculateOptimalCardValueSum() {
        List<Integer> initValues = new ArrayList<>();
        initValues.add(INIT_VALUE_FOR_ADD);
        TotalSumValues initTotalSumValues = new TotalSumValues(initValues);
        List<Integer> handCardValues = getCardValues();
        TotalSumValues totalSumValues = initTotalSumValues.addValuesToAllElement(initTotalSumValues, handCardValues);

        return getCardValueSumMax(totalSumValues, handCardValues);
    }

    private int getCardValueSumMax(TotalSumValues totalValues, List<Integer> handCardValues) {
        int maxSumValue = 0;
        if (handCardValues.contains(Denomination.ACE_VALUE)) {
            maxSumValue = totalValues.getLessThanLimitMaxValue();
        }
        if (!handCardValues.contains(Denomination.ACE_VALUE)) {
            maxSumValue = totalValues.getMaxValue();
        }
        return maxSumValue;
    }

    private List<Integer> getCardValues() {
        return cards.stream()
                .map(Card::getValue)
                .collect(Collectors.toList());
    }

    public boolean isBlackjack() {
        return calculateOptimalCardValueSum() == BLACKJACK_VALUE && cards.size() == BLACKJACK_SIZE;
    }

    public boolean isBust() {
        return calculateOptimalCardValueSum() >= MIN_BUST_VALUE;
    }

    public boolean isStay() {
        return calculateOptimalCardValueSum() < BLACKJACK_VALUE;
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}
