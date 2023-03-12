package domain;

import java.util.List;

public class Cards {

    public static final int BLACKJACK_NUMBER = 21;
    private final List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = cards;
    }

    public int calculateSum() {
        int initialSum = addAllCardValues();
        if (hasAce()) {
            return selectAceValue(initialSum);
        }
        return initialSum;
    }

    private boolean hasAce() {
        return cards.stream()
                .map(Card::getCardValue)
                .anyMatch(value -> value == CardValue.ACE);
    }

    private int addAllCardValues() {
        return cards.stream()
                .map(Card::getCardValue)
                .mapToInt(CardValue::getCardValue)
                .sum();
    }

    private int selectAceValue(int initialSum) {
        if (initialSum > 11) {
            return initialSum;
        }
        return initialSum + 10;
    }

    public boolean isBlackjack() {
        return calculateSum() == BLACKJACK_NUMBER;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getSize() {
        return cards.size();
    }

    //
//    public static final int BLACKJACK_NUMBER = 21;
//    public static final int ADDITIONAL_A_VALUE = 10;
//
//    private final List<Card> cards;
//
//    public Cards(List<Card> cards) {
//        this.cards = cards;
//    }
//
//    public int getScore() {
//        int sum = getSum();
//        if (hasA()) {
//            return calculateAValues(sum);
//        }
//        return sum;
//    }
//
//    private int getSum() {
//        return cards.stream()
//            .map(Card::getNumber)
//            .mapToInt(Number::getValue)
//            .sum();
//    }
//
//    private boolean hasA() {
//        return cards.stream()
//            .anyMatch(c -> c.is(Number.A));
//    }
//
//    private int calculateAValues(int sum) {
//        if (sum + ADDITIONAL_A_VALUE > BLACKJACK_NUMBER) {
//            return sum;
//        }
//        return sum + ADDITIONAL_A_VALUE;
//    }
//
//    public int getSize() {
//        return cards.size();
//    }
//
//    public void add(Card card) {
//        cards.add(card);
//    }
//
//    public Card get(int index) {
//        return cards.get(index);
//    }
}
