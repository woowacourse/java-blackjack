package domain.player;

import domain.card.Card;
import domain.card.Cards;
import domain.state.State;
import java.util.Objects;

public abstract class Player {

    protected final String name;
    protected State state;

    public Player(String name, State state) {
        this.name = name;
        this.state = state;
    }

    public void hit(Card card, boolean open) {
        if (open) {
            card.open();
        }
        this.state = state.hit(card);
    }

    public void stay() {
        this.state = state.stay();
    }

    public boolean isRunning() {
        return !state.isFinished();
    }

    public int computeOptimalSum() {
        return cards().computeOptimalSum();
    }

    public void openCards(int count) {
        cards().openedCards(count);
    }

    public Cards openedCards() {
        return cards().openedCards();
    }

    public Cards cards() {
        return state.cards();
    }

    public String getName() {
        return name;
    }

    public State getState() {
        return state;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Player player)) {
            return false;
        }
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
