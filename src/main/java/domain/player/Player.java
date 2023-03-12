package domain.player;

import domain.card.Card;
import domain.card.Hand;

import java.util.List;
import java.util.Objects;

public abstract class Player {
    private final Hand hand;
    private final Name name;

    public Player(Hand hand, Name name) {
        this.hand = hand;
        this.name = name;
    }

    public void draw(Card card) {
        hand.addCard(card);
    }

    public int getScore() {
        return hand.getScore();
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public List<Card> getCards() {
        return hand.getCards();
    }

    public abstract boolean isNameEqualTo(String playerName);

    public String getName() {
        return this.name.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return name.equals(player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public Card getCardIndexOf(int index) {
        return hand.getCardIndexOf(index);
    }
}
