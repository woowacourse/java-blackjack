package blackjack.model.state;

import blackjack.Hand;
import blackjack.model.card.CardDeck;

public class BlackjackState extends State {

    public BlackjackState(Hand hand) {
        super(hand);
    }

    @Override
    public State draw(CardDeck cardDeck) {
        return null;
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
