package blackjack.domain.user;

import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Player extends User {
    protected final List<Card> cards = new ArrayList<>();
    private final Name name;

    public Player(String name) {
        this.name = new Name(name);
    }

    @Override
    public String getName() {
        return name.getName();
    }

    @Override
    protected List<Card> getCards() {
        return cards;
    }

    @Override
    public boolean isGameOver(int gameOverScore) {
        return calculateScore(gameOverScore) > gameOverScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
