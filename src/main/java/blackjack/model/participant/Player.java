package blackjack.model.participant;

import blackjack.model.Name;
import blackjack.model.card.CardDeck;
import blackjack.model.state.DrawState;
import blackjack.model.state.State;

public class Player extends Participant {

    public Player(Name name, State currentState) {
        super(name, currentState);
    }

    @Override
    public void play(CardDeck cardDeck) {
        this.currentState = currentState.draw(cardDeck);
    }

    @Override
    public void changeToStand() {
        if(currentState instanceof DrawState){
            this.currentState = ((DrawState)currentState).turnStandState();
        }
    }
}
