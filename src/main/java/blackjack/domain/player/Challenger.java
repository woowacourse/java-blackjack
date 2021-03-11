package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.player.state.NonBlackJackState;
import blackjack.domain.player.state.State;
import blackjack.domain.result.Result;

import java.util.List;

public class Challenger extends Player {

    private State state;

    public Challenger(final Cards cards, final Name name) {
        super(cards, name);
        state = new NonBlackJackState();
    }

    public Result getResult(final Dealer dealer) {
        if (isBust()) {
            return Result.LOSE;
        }
        state.blackJackCheck(dealer, this);
        state.compareCards(dealer, this);
        return state.getResult();
    }

    public void changeState(final State newSate) {
        this.state = newSate;
    }

    @Override
    public List<Card> getInitCards() {
        return hand.getList();
    }
}
