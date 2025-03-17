package domain.gamer;

import domain.deck.Card;
import domain.state.State;
import java.util.List;

public class Dealer extends Gamer {

    public Dealer(final Nickname nickname, final State state) {
        super(nickname, state);
    }

    public State getState() {
        return state;
    }

    @Override
    public List<Card> getInitialCards() {
        final Hand hand = getHand();
        final List<Card> cards = hand.getCards();
        return List.of(cards.getFirst());
    }

    @Override
    public int calculateSumOfRank() {
        return getHand().calculateSumOfRank();
    }

    @Override
    public String getDisplayName() {
        return super.getDisplayName();
    }

    @Override
    public Nickname getNickname() {
        return super.getNickname();
    }
}
