package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {

    private final List<Card> value;

    public Cards() {
        this.value = new ArrayList<>();
    }

    public boolean isBlackjack() {
        return isReady() && hasAce() && totalScore() == 11;
    }

    public boolean isReady() {
        return value.size() == 2;
    }

    private boolean hasAce() {
        return value.stream()
                .anyMatch(Card::isAce);
    }

    public boolean isBust() {
        return totalScore() > 21;
    }

    public int totalScore() {
        return value.stream()
                .map(Card::getDenomination)
                .mapToInt(Denomination::getScore)
                .sum();
    }

    public void append(Card card) {
        value.add(card);
    }

    public List<Card> getValue() {
        return Collections.unmodifiableList(value);
    }
}
