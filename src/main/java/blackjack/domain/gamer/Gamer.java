package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.state.Blackjack;
import blackjack.domain.state.Bust;
import blackjack.domain.state.FirstTurn;
import blackjack.domain.state.State;

import java.util.List;
import java.util.Objects;

public abstract class Gamer {

    //
    protected State state;

//    protected final Hands hands = new Hands();
    private final String name;

    protected Gamer(String name) {
        this.name = name;
    }

//    public abstract List<Card> showOpenHands();

    public abstract List<Card> showOpenHands2();

//    public List<Card> showHands() {
//        return hands.toList();
//    }

    public List<Card> showHands2() {
        return state.cards().toList();
    }

    public String getName() {
        return name;
    }

//    public int getScore() {
//        return hands.calculate();
//    }

    public int getScore2() {
        return state.cards().calculate();
    }


//    public boolean canDraw() {
//        return !(hands.isBust() && hands.isBlackjack());
//    }

    public boolean canDraw2() {
        return !state.isFinished();
    }

//    public boolean isBlackjack() {
//        return hands.isBlackjack();
//    }

//    public void receiveCard(Card card) {
//        hands.addCard(card);
//    }

    public void receiveCard2(Card card) {
        state = state.draw(card);
    }

//    public void initHands(List<Card> makeInitialHands) {
//        hands.initialize(makeInitialHands);
//    }

    //
    public void initState(List<Card> initCards) {
        state = FirstTurn.draw(initCards);
    }

    public boolean isBust() {
        return Objects.equals(state, Bust.class);
    }

    public boolean isBlackjack() {
        return Objects.equals(state, Blackjack.class);
    }
}
