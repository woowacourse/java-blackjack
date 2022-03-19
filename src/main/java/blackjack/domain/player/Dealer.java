package blackjack.domain.player;

import blackjack.domain.Outcome;
import blackjack.domain.card.Deck;
import blackjack.domain.state.Ready;

public class Dealer extends AbstractPlayer implements Player {

    private static final String DEALER_NAME = "딜러";

    public Dealer(Deck deck) {
        this.name = new Name(DEALER_NAME);
        this.state = Ready.dealToDealer(deck.pick(), deck.pick());
    }

    @Override
    public boolean isDealer() {
        return true;
    }

    @Override
    public String toString() {
        return "Dealer{" +
                "name=" + name +
                ", playerCards=" + state.getCards() +
                '}';
    }
}
