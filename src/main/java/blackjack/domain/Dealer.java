package blackjack.domain;

import blackjack.domain.state.InitialState;
import blackjack.domain.state.State;
import java.util.ArrayList;
import java.util.List;

public class Dealer extends Participant {

    private static final int STAND_LIMIT_VALUE = 17;

    public Dealer(Name name, State state) {
        super(name, state);
    }

    public static Dealer createInitialStateDealer() {
        return new Dealer(new Name("딜러"), new InitialState());
    }

    @Override
    public Dealer draw(Deck deck) {
        State newState = getState().draw(deck);
        return new Dealer(getName(), newState);
    }

    @Override
    public Dealer stand() {
        State newState = getState().stand();
        return new Dealer(getName(), newState);
    }

    @Override
    public boolean canDraw() {
        State state = getState();
        Score score = state.calculateHand();
        return !state.isFinished() && isDealerScoreUnderLimit(score);
    }

    private boolean isDealerScoreUnderLimit(Score score) {
        return Score.from(STAND_LIMIT_VALUE).isBiggerThanOrEqual(score);
    }

    public List<Card> getVisibleCards() {
        List<Card> cards = getCards();
        ArrayList<Card> copiedCards = new ArrayList<>(cards);
        return copiedCards.subList(0, copiedCards.size() - 1);
    }
}
