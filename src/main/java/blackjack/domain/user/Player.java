package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Score;
import blackjack.domain.state.State;
import blackjack.domain.state.StateFactory;

import java.util.List;
import java.util.Objects;

public class Player extends User {
    private final Name name;
    private State state;
    private final Money money;

    public Player(String name, int money, List<Card> cards) {
        this.name = new Name(name);
        this.money = new Money(money);
        this.state = StateFactory.createState(cards.get(0), cards.get(1));
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
    public void addCard(Card card) {
        this.state = state.draw(card);
    }

    @Override
    public int scoreToInt() {
        return state.score().toInt();
    }

    @Override
    public Score score() {
        return state.score();
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
