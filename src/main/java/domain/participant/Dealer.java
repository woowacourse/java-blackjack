package domain.participant;

import domain.card.Card;
import domain.card.Deck;
import domain.state.State;
import java.util.List;

public class Dealer extends Participant {

    public Dealer(final List<Card> cards) {
        super(new Name("딜러"), cards);
    }

    public void fillCards(final Deck deck) {
        while (calculateScore() <= 16) {
            this.receiveCard(deck.getCard());
        }
    }

    public State getState() {
        return this.state;
    }
}
