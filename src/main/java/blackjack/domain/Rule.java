package blackjack.domain;

public class Rule {
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
