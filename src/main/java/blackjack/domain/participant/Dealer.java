package blackjack.domain.participant;

import blackjack.domain.card.Score;
import blackjack.domain.result.BlackjackMatch;
import blackjack.domain.state.Ready;
import blackjack.domain.state.State;

public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";

    public Dealer(State state) {
        super(DEALER_NAME, state);
    }

    public Dealer() {
        this(new Ready());
    }

    @Override
    public boolean hasNextTurn() {
        return new Score(this.getScore()).isPossibleDealerHit();
    }

    @Override
    public BlackjackMatch isWin(Participant player) {
        if (player.getStatus().isBust()) {
            return BlackjackMatch.WIN;
        }
        return getStatus().showMatch(player.getStatus());
    }
}
