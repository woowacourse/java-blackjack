package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Participant {

    private static final int BLACK_JACK = 21;
    private static final int ACE_ADDITIONAL_VALUE = 10;

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

        if ((sum > BLACK_JACK) && hasACE()) {
            return (sum - ACE_ADDITIONAL_VALUE);
        }

        return sum;
    }

    private boolean hasACE() {
        return cards.stream()
                .anyMatch(Card::isACE);
    }

    public boolean isBust() {
        return computeSumOfCards() > BLACK_JACK;
    }

    public boolean isAvailable() {
        return !(isBlackJack()) && !(isBust());
    }

    private boolean isBlackJack() {
        return computeSumOfCards() == BLACK_JACK;
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }

    public String getName() {
        return name;
    }
}
