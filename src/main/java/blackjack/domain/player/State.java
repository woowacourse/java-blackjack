package blackjack.domain.player;

import blackjack.domain.Rule;

public enum State {

    BLACKJACK,
    BUST,
    NONE,
    ;

    private static final int  BLACKJACK_SIZE = 2;

    public static State from(final Player player) {
        if (player.getTotalScore() > Rule.WINNING_SCORE.getValue()) {
            return BUST;
        }
        if (player.getCardsSize() == BLACKJACK_SIZE && player.getTotalScore() == Rule.WINNING_SCORE.getValue()) {
            return BLACKJACK;
        }
        return NONE;
    }
}
