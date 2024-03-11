package model.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {

    private final static int MAXIMUM_SUM = 21;

    private List<Card> cards;

    public Cards(List<Card> cards) {
        validateCardSize(cards);
        this.cards = new ArrayList<>(cards);
    }

    private void validateCardSize(List<Card> cards) {
        if (cards.size() != 2) {
            throw new IllegalArgumentException("참가자의 초기 카드는 2장입니다.");
        }
    }

    public void addCards(List<Card> card) {
        cards.addAll(card);
    }

    public void addCard(Card card) {
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

    public boolean isNotHit() {
        return calculateScore() > MAXIMUM_SUM;
    }

    public int findPlayerDifference() {
        return Math.abs(MAXIMUM_SUM - calculateScore());
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
