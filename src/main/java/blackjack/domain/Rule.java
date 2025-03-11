package blackjack.domain;

import blackjack.domain.gambler.Name;

public class Rule {
    public static final Name DEALER_NAME = new Name("딜러");
    public static final int BLACK_JACK = 21;
    public static final int DEALER_RECEIVE_CRITERIA = 16;
    public static final int MAX_ACE_VALUE = 11;
    private static final int MIN_ACE_VALUE = 1;

    private Rule() {
    }

    public static int adjustSumByAce(int sum, int aceCount) {
        if (sum <= BLACK_JACK) {
            return sum;
        }
        while (aceCount > 0 && sum > BLACK_JACK) {
            sum -= (MAX_ACE_VALUE - MIN_ACE_VALUE);
            aceCount--;
        }
        return sum;
    }
}
