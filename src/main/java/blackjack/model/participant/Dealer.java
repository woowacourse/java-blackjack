package blackjack.model.participant;

import blackjack.model.card.Card;
import blackjack.model.card.CardDeck;
import blackjack.model.state.State;

import java.util.Collection;

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
        return currentState.getHand().get(DEALER_FIRST_CARD);
    }

    public int getProfit(Collection<Integer> playerProfitResult) {
        return -1 * playerProfitResult.stream()
                .mapToInt(value -> value)
                .sum();
    }
}
