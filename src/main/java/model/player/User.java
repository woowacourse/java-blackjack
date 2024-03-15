package model.player;

import model.card.Card;
import model.card.Cards;

import java.util.Objects;

public abstract class User {

    protected final String name;
    protected final Cards cards;

    public User(String name, Cards cards) {
        validateNotBlankName(name);
        this.name = name;
        this.cards = cards;
    }

    private void validateNotBlankName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("참가자의 이름은 공백이거나 null일 수 없습니다.");
        }
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public int calculateScore() {
        return cards.calculateScore();
    }

    public int findPlayerDifference() {
        return cards.findPlayerDifference();
    }

    public boolean isNotHit() {
        return cards.isNotHit();
    }

    public boolean isHit() {
        return cards.isHit();
    }

    public String getName() {
        return name;
    }

    public Cards getCards() {
        return cards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
