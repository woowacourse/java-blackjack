package blackjack.model.participant;

import blackjack.model.Name;
import blackjack.model.card.CardDeck;
import blackjack.model.state.DrawState;
import blackjack.model.state.State;

public class Dealer extends Participant{

    public Dealer(State currentState) {
        super(new Name("딜러"), currentState);
    }

    @Override
    public void play(CardDeck cardDeck) {
        this.currentState = currentState.draw(cardDeck);
        if(currentState instanceof DrawState){
            this.currentState = ((DrawState)currentState).turnDealerDrawState();
        }
    }

    @Override
    public void changeToStand() {
        if(currentState instanceof DrawState){
            this.currentState = ((DrawState)currentState).turnStandState();
        }
    }

}
