package domain.player;

import domain.card.Card;
import domain.card.Cards;

import java.util.List;
import java.util.Objects;

public abstract class Player {
    private final Cards cards;
    private final String name;

    public Player(String name) {
        this.cards = new Cards();
        this.name = name;
    }

    public void addCard(Card card) {
        cards.addCard(card);
    }

    public int getTotalScore() {
        return cards.getTotalScore();
    }

    public boolean isBust() {
        return cards.isBust();
    }

    public List<Card> getCards() {
        return cards.getCards();
    }

    public abstract boolean isNameEqualTo(String playerName);

    public String getName() {
        return this.name;
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
        return cards.getCardIndexOf(index);
    }
}
