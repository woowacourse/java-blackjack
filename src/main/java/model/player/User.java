package model.player;

import java.util.List;
import java.util.Objects;
import model.Outcome;
import model.card.Card;
import model.card.Cards;

public abstract class User {

    protected final String name;
    protected Cards cards;

    public User(String name, List<Card> cards) {
        validateName(name);
        this.name = name;
        this.cards = new Cards(cards);
    }

    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("참가자의 이름은 공백이거나 null일 수 없습니다.");
        }
    }

    public void addCards(List<Card> card) {
        cards.addCards(card);
    }

    public void addCard(Card card) {
        cards.addCard(card);
    }

    public int calculateScore() {
        return cards.calculateScore();
    }

    public boolean isNotHit() {
        return cards.isNotHit();
    }

    public boolean isHit() {
        return cards.isHit();
    }

    public int findPlayerDifference() {
        return cards.findPlayerDifference();
    }

    protected Outcome findPlayerOutcome(int otherDifference) {
        int difference = findPlayerDifference();
        if (otherDifference > difference) {
            return Outcome.WIN;
        }
        if (otherDifference < difference) {
            return Outcome.LOSE;
        }
        return Outcome.DRAW;
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
