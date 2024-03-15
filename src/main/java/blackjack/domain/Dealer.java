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
        return new Dealer(getName(), drawHand(deck));
    }

    @Override
    public Dealer stand() {
        return new Dealer(getName(), standHand());
    }

    @Override
    public boolean canDraw() {
        return !isFinished() && isDealerScoreUnderLimit(calculateHand());
    }

    private boolean isDealerScoreUnderLimit(Score score) {
        return Score.from(STAND_LIMIT_VALUE).isBiggerThan(score);
    }

    public List<Card> getVisibleCards() {
        List<Card> cards = getCards();
        ArrayList<Card> copiedCards = new ArrayList<>(cards);
        return copiedCards.subList(0, copiedCards.size() - 1);
    }
}
