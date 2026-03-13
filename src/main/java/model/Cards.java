package model;

import java.util.List;

public final class Cards {
    private final List<Card> values;

    private Cards(List<Card> values) {
        this.values = values;
    }


    public static Cards of(List<Card> hands) {
        return new Cards(hands);
    }

    public Cards add(Card card) {
        values.add(card);

        return this;
    }

    public int size() {
        return values.size();
    }

    public int calculateScore() {
        return values.stream()
                .map(Card::getCardNumber)
                .mapToInt(CardNumber::toValue)
                .sum();
    }

    public int aceCount() {
        int count = 0;
        for (Card card : values) {
            if (card.getCardNumber() == CardNumber.ACE) {
                count++;
            }
        }

        return count;
    }

    public boolean contains(Card card) {
        return values.contains(card);
    }

    public List<String> open() {
        return values.stream()
                .map(Card::toString)
                .toList();
    }

    public String getFirst() {
        return values.getFirst().toString();
    }
}
