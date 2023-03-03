package blackjack.model.participant;

import blackjack.model.card.Card;
import blackjack.model.card.CardDeck;
import blackjack.model.state.DealerDrawState;
import blackjack.model.state.DrawState;
import blackjack.model.state.State;

public class Dealer extends Participant {

    private static final int DEALER_FIRST_CARD = 0;

    public Dealer(State currentState) {
        super(new Name("딜러"), currentState);
    }

    @Override
    public void play(CardDeck cardDeck) {
        this.currentState = currentState.draw(cardDeck);
        if (currentState instanceof DrawState) {
            this.currentState = ((DrawState) currentState).turnDealerDrawState();
            this.currentState = ((DealerDrawState)currentState).checkStandOrBustState();
        }
    }

    public Card getFirstCard(){
        return currentState.getHand().getCards().get(DEALER_FIRST_CARD);
    }

}
