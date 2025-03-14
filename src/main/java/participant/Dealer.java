package participant;

import card.Card;
import deck.Deck;
import java.util.List;
import state.started.Started;

public class Dealer extends Participant {

    public static final int DEALER_MAX_NUMBER_FOR_BUST = 16;

    private Dealer(final Card card1, final Card card2) {
        super(Started.start(card1, card2));
    }

    public static Dealer prepareGame(Deck deck) {
        return new Dealer(deck.draw(), deck.draw());
    }

    public Card firstRoundCard() {
        return state.cards().get(1);
    }

    @Override
    public boolean canReceiveCard() {
        return score() <= DEALER_MAX_NUMBER_FOR_BUST;
    }

    public List<Card> cards() {
        return state.cards().getCards();
    }
}
