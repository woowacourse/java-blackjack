package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

abstract class Participant {

    static final int SUM_MAXIMUM_BEFORE_BUST = 21;
    static final int ACE_ADDITIONAL_VALUE = 10;

    private final String name;
    private final List<Card> cards;

    public Participant(String name) {
        this.name = name;
        validateName(name);
        cards = new ArrayList<>();
    }

    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("이름은 빈 문자열이거나 공백일 수 없습니다.");
        }
    }

    public void take(Card card) {
        cards.add(card);
    }

    public int computeSumOfCards() {
        int sum = cards.stream()
                .map(Card::getNumberValue)
                .reduce(0, Integer::sum);

        if ((sum > SUM_MAXIMUM_BEFORE_BUST) && hasACE()) {
            return (sum - ACE_ADDITIONAL_VALUE);
        }

        return sum;
    }

    private boolean hasACE() {
        return cards.stream()
                .anyMatch(Card::isACE);
    }

    public boolean isBust() {
        return computeSumOfCards() > SUM_MAXIMUM_BEFORE_BUST;
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }

    public String getName() {
        return name;
    }

    abstract boolean isAvailable();
}
