package blackjack.domain;

import blackjack.common.Constants;
import blackjack.common.ErrorMessage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Hand  {

    private final List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    private Hand(List<Card> cards) {
        this.cards = cards;
    }

    public static Hand of(Card card1, Card card2) {
        validate(card1, card2);
        List<Card> cards = new ArrayList<>(List.of(card1, card2));
        return new Hand(cards);
    }

    private static void validate(Card card1, Card card2) {
        if (card1 == null || card2 == null) {
            throw new IllegalArgumentException(ErrorMessage.CAN_NOT_NULL.getMessage());
        }
    }

    public List<Card> getAllCards() {
        return Collections.unmodifiableList(cards);
    }

    public void takeCard(Card newCard) {
        cards.add(newCard);
    }

    public int getOptimisticValue() {
        return getPossibleSums().stream()
                .filter(sum -> sum <= Constants.BUSTED_STANDARD_VALUE)
                .max(Comparator.naturalOrder())
                .orElse(Constants.BUSTED_VALUE);
    }

    public Card getCard(int position) {
        if (position < 0 || position >= cards.size()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_CARD_INDEX.getMessage());
        }

        return cards.get(position);
    }

    public boolean isBusted() {
        List<Integer> possibleSums = getPossibleSums();

        return possibleSums.stream()
                .allMatch(sum -> sum > Constants.BUSTED_STANDARD_VALUE);
    }

    public boolean canTakeCardWithin(int takeBoundary) {
        List<Integer> possibleSums = getPossibleSums();

        return possibleSums.stream()
                .anyMatch(sum -> sum <= takeBoundary);
    }

    private List<Integer> getPossibleSums() {
        Set<Integer> possibleSums = new HashSet<>();

        calculatePossibleSums(possibleSums, 0, 0);
        return possibleSums.stream().toList();
    }

    private void calculatePossibleSums(Set<Integer> values, int index, int sum) {
        if (cards.size() == index) {
            values.add(sum);
            return;
        }

        Card card = cards.get(index);
        for (int number : card.getValue()) {
            calculatePossibleSums(values, index + 1, sum + number);
        }
    }

    // 블랙잭 여부 반환
    public boolean isBlackJack(){
        return cards.size() == 2 && getOptimisticValue() == 21;
    }


    // TODO 딜러와의 카드 비교를 하여 승패 여부를 반환한다.
    // 우선은 여기다 만들고 ENUM으로 어떻게 처리할 지 생각해본다.
}
