package model.card;

import model.BlackJackState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public record Cards(List<Card> cards) {

    private final static int INITIAL_CARD_SIZE = 2;
    private final static int MAXIMUM_SUM = 21;

    public Cards(List<Card> cards) {
        validateCardSize(cards);
        this.cards = new ArrayList<>(cards);
    }

    private void validateCardSize(List<Card> cards) {
        if (cards.size() != INITIAL_CARD_SIZE) {
            throw new IllegalArgumentException("참가자의 초기 카드는 " + INITIAL_CARD_SIZE + "장입니다.");
        }
    }

    public void add(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        int sum = cards.stream()
                .mapToInt(Card::minimumNumber)
                .sum();

        List<Integer> differenceNumbers = mapDifferenceNumber();
        return differenceNumbers.stream()
                .reduce(sum, this::changeToBestNumber);
    }

    private List<Integer> mapDifferenceNumber() {
        return cards.stream()
                .map(Card::subtractMaxMinNumber)
                .filter(subtractNumber -> subtractNumber != 0)
                .toList();
    }

    private int changeToBestNumber(Integer result, Integer number) {
        if (result + number <= MAXIMUM_SUM) {
            return result + number;
        }
        return result;
    }

    public int findPlayerDifference() {
        return Math.abs(MAXIMUM_SUM - calculateScore());
    }

    public BlackJackState findBlackJackState() {
        return BlackJackState.createBlackJackState(calculateScore(), cards.size());
    }

    public boolean isNotHit() {
        return calculateScore() > MAXIMUM_SUM;
    }

    public boolean isHit() {
        return !isNotHit();
    }

    @Override
    public List<Card> cards() {
        return Collections.unmodifiableList(cards);
    }
}
