package domain.player;

import domain.card.Card;
import domain.card.Cards;

import java.util.Objects;

public class Player implements Participant {
    private final String name;
    private final Cards cards;

    public Player(String name, Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    @Override
    public boolean isFinished() {
        return cards.isBust() || cards.isBlackJack();
    }

    @Override
    public void drawCard(Card card) {
        cards.add(card);
    }

    public String getName() {
        return name;
    }

    public Cards getCards() {
        return cards;
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
}
