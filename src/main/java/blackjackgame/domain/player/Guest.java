package blackjackgame.domain.player;

import java.util.List;
import java.util.Objects;

import blackjackgame.domain.card.Card;
import blackjackgame.domain.state.Ready;
import blackjackgame.domain.state.State;

public class Guest extends Player {
    private final Name name;
    private final int money;

    private Guest(State state, final Name name, final int money) {
        super(state);
        this.name = name;
        this.money = money;
    }

    public static Guest of(List<Card> cards, Name name, int money) {
        State state = new Ready();
        for (Card card : cards) {
            state = state.draw(card);
        }
        return new Guest(state, name, money);
    }

    @Override
    public List<Card> startingCards() {
        return cards();
    }

    public boolean canHit() {
        return isRunning();
    }

    public String getName() {
        return name.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Guest guest = (Guest)o;
        return Objects.equals(name, guest.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public void setStay() {
        super.stay();
    }

    public int bettingMoney() {
        return money;
    }
}
