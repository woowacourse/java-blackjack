package domain.player;

import domain.card.Card;
import domain.card.CardHolder;

import java.util.List;
import java.util.Objects;

public abstract class Player {
    private final CardHolder cardHolder;
    private final Name name;

    public Player(CardHolder cardHolder, Name name) {
        this.cardHolder = cardHolder;
        this.name = name;
    }

    public void addCard(Card card) {
        cardHolder.addCard(card);
    }

    public int getTotalScore() {
        return cardHolder.getTotalScore();
    }

    public boolean isBust() {
        return cardHolder.isBust();
    }

    public List<Card> getCards() {
        return cardHolder.getCards();
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
        return cardHolder.getCardIndexOf(index);
    }
}
