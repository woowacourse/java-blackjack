package blackjack.model.participant;

import blackjack.model.card.Card;
import blackjack.model.card.CardDeck;
import blackjack.model.state.DealerDrawState;
import blackjack.model.state.PlayerDrawState;
import blackjack.model.state.State;

public class Dealer extends Participant {

    private static final int DEALER_FIRST_CARD = 0;
    private static final String DEALER_NAME = "딜러";

    public Dealer(State currentState) {
        super(new Name(DEALER_NAME), currentState);
    }

    @Override
    public void play(CardDeck cardDeck) {
        this.currentState = currentState.draw(cardDeck);
    }

    public Card getFirstCard() {
        return currentState.getHand().getCards().get(DEALER_FIRST_CARD);
    }

}
