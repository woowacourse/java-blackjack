package blackjack.domain.player;

import static blackjack.domain.Rule.BLACKJACK_SIZE;

import blackjack.domain.Rule;

public enum State {

    BLACKJACK,
    BUST,
    NONE,
    ;

    public static State from(final Player player) {
        if (player.getTotalScore() > Rule.WINNING_SCORE.getValue()) {
            return BUST;
        }
        if (player.getCardsSize() == BLACKJACK_SIZE.getValue() && player.getTotalScore() == Rule.WINNING_SCORE.getValue()) {
            return BLACKJACK;
        }
        return NONE;
    }
}
