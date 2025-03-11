package blackjack.domain;

import blackjack.domain.gambler.Name;

public class Rule {
    public static final Name DEALER_NAME = new Name("딜러");
    public static final int BLACK_JACK = 21;
    public static final int DEALER_RECEIVE_CRITERIA = 16;
    public static final int MAX_ACE_VALUE = 11;
    public static final int MIN_ACE_VALUE = 1;

    private Rule() {
    }
}
