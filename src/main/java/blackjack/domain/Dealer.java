package blackjack.domain;

import blackjack.view.OutputView;

public class Dealer extends Gamer {

    private static final int POINT_BOUNDARY_VALUE = 16;
    private static final String DEFAULT_DEALER_NAME = "딜러";

    public Dealer() {
        super(DEFAULT_DEALER_NAME);
    }

    @Override
    public boolean canReceiveCard() {
        return state.cards().getPoint(POINT_BOUNDARY_VALUE) <= POINT_BOUNDARY_VALUE;
    }

    @Override
    public Boolean continueDraw(Deck deck) {
        this.receiveCard(deck.dealCard());
        OutputView.noticeDealerGetCard();
        return true;
    }
}
