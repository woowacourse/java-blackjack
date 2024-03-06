package domain;

import java.util.Objects;

public class Player {
    private final Name name;
    private final Cards cards;

    public Player(String name) {
        this.name = new Name(name);
        this.cards = new Cards();
    }

    public void add(Card card) {
        cards.addCard(card);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player player = (Player) o;
        return Objects.equals(name, player.name) && Objects.equals(cards, player.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cards);
    }
}
