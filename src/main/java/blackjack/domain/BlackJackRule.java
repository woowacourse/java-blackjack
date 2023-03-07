package blackjack.domain;

import blackjack.domain.user.User;

public class BlackJackRule {

    private static final int BLACK_JACK_NUMBER = 21;
    private static final int ACE_OFFSET = -10;

    private BlackJackRule() {
    }

    public static int getScore(final User user) {
        int score = user.getScore();
        int aceCount = user.getAceCount();

        while (score > BLACK_JACK_NUMBER && aceCount > 0) {
            score += ACE_OFFSET;
            aceCount--;
        }
        return score;
    }

    public static boolean isBust(final User user) {
        return getScore(user) > BLACK_JACK_NUMBER;
    }

    public static boolean isBlackJackScore(final User user) {
        return getScore(user) == BLACK_JACK_NUMBER;
    }
}
