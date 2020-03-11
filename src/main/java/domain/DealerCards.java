package domain;

import util.BlackJackRule;

public class DealerCards extends Cards {
    public boolean isOverSixteen() {
        return BlackJackRule.isOverSixteen(getScore());
    }
}
