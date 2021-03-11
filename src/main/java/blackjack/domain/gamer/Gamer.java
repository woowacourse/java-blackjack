package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.state.Blackjack;
import blackjack.domain.state.FirstTurn;
import blackjack.domain.state.State;
import blackjack.domain.state.Stay;

import java.util.List;

public abstract class Gamer {

    private final String name;
    protected State state;

    protected Gamer(final String name) {
        this.name = name;
    }

    public List<Card> showHands() {
        return cards().toList();
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return cards().calculate();
    }

    public void receiveCard(final Card card) {
        state = state.draw(card);
    }

    public void initState(final List<Card> initCards) {
        state = FirstTurn.draw(initCards);
    }

    public boolean isStay() {
        return state instanceof Stay;
    }

    public boolean isBlackjack() {
        return state instanceof Blackjack;
    }

    public Cards cards() {
        return state.cards();
    }

    public abstract List<Card> showOpenHands();

    public abstract boolean canDraw();

    public void finish() {
        state = state.stay();
    }
}
