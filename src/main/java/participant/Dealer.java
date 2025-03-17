package participant;

import card.Card;
import card.CardHand;
import java.util.List;
import result.GameResult;
import state.finished.Finished;
import state.running.DealerHit;
import state.running.Running;

public final class Dealer extends Participant {
    private static final String DEALER_NAME = "딜러";

    public Dealer(final CardHand initialHand) {
        super(DealerHit.initialState(initialHand));
    }

    @Override
    public List<Card> openInitialCard() {
        CardHand cardHand = state.cardHand();
        return List.of(cardHand.getFirstCard());
    }

    public GameResult judgeResult(final Player player) {
        if (this.state instanceof Running || player.state instanceof Running) {
            throw new IllegalStateException();
        }
        Finished playerState = (Finished) player.state;
        return playerState.calculatePlayerResult((Finished) this.state);
    }

    @Override
    public Name getName() {
        return new Name(DEALER_NAME);
    }
}
