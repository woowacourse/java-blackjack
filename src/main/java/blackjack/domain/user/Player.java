package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.state.State;
import blackjack.domain.state.StateFactory;

import java.util.List;
import java.util.Objects;

public class Player extends User {
    private final Name name;
    private State state;

    public Player(String name) {
        this.name = new Name(name);
    }

    public String getState() {
        return state.toString();
    }

    @Override
    public String getName() {
        return name.getName();
    }

    @Override
    protected List<Card> getCards() {
        return state.getCards();
    }

    @Override
    public void addFirstCards(Card card1, Card card2) {
        this.state = StateFactory.createState(card1, card2);
    }

    @Override
    public void addCard(Card card) {
        this.state = state.draw(card);
    }

    @Override
    public int score() {
        return state.score().toInt();
    }

    @Override
    public boolean isGameOver() {
        return state.isGameOver();
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
