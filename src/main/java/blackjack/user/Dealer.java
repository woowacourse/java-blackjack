package blackjack.user;

import blackjack.Cards;
import blackjack.State;

public class Dealer extends Participant {

    private static final String NAME = "딜러";
    private static final int OPEN_INIT_CARD_LOGIC = 0;

    private Dealer() {
        super(NAME);
    }

    public static Dealer generate() {
        return new Dealer();
    }

    @Override
    protected void setStateStayIfSatisfied(boolean stayFlag) {
        if (state.equals(State.HIT) && myCards.scoreSum() > 16) {
            state = State.STAY;
        }
    }

    @Override
    protected void updateStateAfterAddCard() {
        setStateBlackjackIfSatisfied();
        setStateBustIfSatisfied();
        setStateStayIfSatisfied(true);
    }

    @Override
    public Cards pickOpenCards() {
        Cards openCards = Cards.generateEmpty();
        openCards.addCard(myCards.pick(OPEN_INIT_CARD_LOGIC));
        return openCards;
    }
}
