package blackjack.domain.participant;

import blackjack.domain.result.MatchResult;

public class Dealer extends Participant {
    public static final Nickname DEALER_NAME = new Nickname("딜러");

    public Dealer() {
        super(DEALER_NAME);
    }

    public MatchResult matchGame(Player player) {
        return MatchResult.getPlayerMatchResult(player.state, state);
    }

    @Override
    public boolean canDraw() {
        return !state.isFinished() && state.getCards().lessThanSixteen();
    }
}

