package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

abstract class Participant {

    private final String name;
    private final Cards cards;

    public Participant(String name) {
        this.name = name;
        validateName(name);
        cards = new Cards(new ArrayList<>());
    }

    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("이름은 빈 문자열이거나 공백일 수 없습니다.");
        }
    }

    public void take(Card card) {
        cards.add(card);
    }

    public int computeCardsScore() {
        return cards.sumScore();
    }

    public boolean isBust() {
        return cards.hasBustedScore();
    }

    public boolean isSafe() {
        return cards.hasSafeScore();
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards.cards();
    }

    abstract boolean isAvailable();
}
