package blackjack.domain.participant;

import blackjack.domain.card.Deck;
import blackjack.domain.state.InitialParticipantState;
import blackjack.domain.state.ParticipantState;

public class Player extends Participant {

    public Player(Name name, ParticipantState participantState) {
        super(name, participantState);
    }

    public static Player createInitialStatePlayer(Name name) {
        return new Player(name, new InitialParticipantState());
    }

    @Override
    public Player draw(Deck deck) {
        return new Player(getName(), drawHand(deck));
    }

    @Override
    public Player stand() {
        return new Player(getName(), standHand());
    }

    public Player decideHitOrStand(boolean hit, Deck deck) {
        if (hit && canDraw()) {
            return draw(deck);
        }
        return stand();
    }
}
