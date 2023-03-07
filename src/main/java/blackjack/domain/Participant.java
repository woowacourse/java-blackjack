package blackjack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

abstract class Participant {

    private final String name;
    private final Hand hand;

    public Participant(String name) {
        this.name = name;
        validateName(name);
        hand = new Hand(new ArrayList<>());
    }

    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("이름은 빈 문자열이거나 공백일 수 없습니다.");
        }
    }

    public void take(Card card) {
        hand.add(card);
    }

    public int computeCardsScore() {
        return hand.sumScore();
    }

    public boolean isBust() {
        return hand.hasBustedScore();
    }

    public boolean isSafe() {
        return hand.hasSafeScore();
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return hand.cards();
    }

    abstract boolean isAvailable();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Participant that = (Participant) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
