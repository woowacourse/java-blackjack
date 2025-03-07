package blackjack.domain;

import blackjack.domain.gambler.Name;

public class Rule {
    public static final Name DEALER_NAME = new Name("딜러");
    private Rule() {
    }

    public static int adjustSumByAce(int sum, int aceCount) {
        if (sum <= 21) {
            return sum;
        }
        while (aceCount > 0 && sum > 21) {
            sum -= 10;
            aceCount--;
        }
        return sum;
    }
}
