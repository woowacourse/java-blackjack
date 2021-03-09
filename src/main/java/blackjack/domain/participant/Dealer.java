package blackjack.domain.participant;

import blackjack.domain.result.MatchResult;

public class Dealer extends Participant {
    public static final Nickname DEALER_NAME = new Nickname("딜러");
    private static final int DEALER_DRAW_CONDITION = 16;

    public Dealer() {
        super(DEALER_NAME);
    }

    public MatchResult matchGame(Player player) {
        int playerScore = player.state.getCards().calculateScore();
        int dealerScore = state.getCards().calculateScore();
        return MatchResult.getPlayerMatchResult(playerScore, dealerScore);
    }

    @Override
    public boolean canDraw() {
        return this.state.getCards().calculateScore() <= DEALER_DRAW_CONDITION;
    }
}

