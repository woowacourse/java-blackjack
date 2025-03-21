package blackjack.state.started.ready;

import blackjack.card.CardDeck;
import blackjack.card.Hand;
import blackjack.state.State;
import blackjack.state.started.finished.Blackjack;
import blackjack.state.started.running.Hit;

public class InitialDealing extends Ready {

    public InitialDealing(Hand hand) {
        super(hand);
    }

    @Override
    public State initialDeal(CardDeck cardDeck) {
        hand.add(cardDeck.drawCard());
        hand.add(cardDeck.drawCard());

        if (hand.isBlackjack()) {
            hand.finishInitialDeal();
            return new Blackjack(hand);
        }

        hand.finishInitialDeal();
        return new Hit(hand);

    }
}
