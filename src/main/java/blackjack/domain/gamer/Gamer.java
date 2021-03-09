package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.state.Blackjack;
import blackjack.domain.state.Bust;
import blackjack.domain.state.FirstTurn;
import blackjack.domain.state.State;

import java.util.List;
import java.util.Objects;

public abstract class Gamer {

    private final String name;
    protected State state;

    protected Gamer(String name) {
        this.name = name;
    }

    public List<Card> showHands2() {
        return state.cards().toList();
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return state.cards().calculate();
    }

    public boolean canDraw() {
        return !state.isFinished();
    }

    public void receiveCard2(Card card) {
        state = state.draw(card);
    }

    public void initState(List<Card> initCards) {
        state = FirstTurn.draw(initCards);
    }

    public boolean isBust() {
        return state instanceof Bust;
    }

    public boolean isBlackjack() {
        return Objects.equals(state, Blackjack.class);
    }

    public abstract List<Card> showOpenHands();
}
