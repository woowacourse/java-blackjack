package blackjack.domain;

public class BetAmount {

    public static final int MIN = 10000;
    public static final int MAX = 100000;

    public BetAmount(int amount) {
        if (MIN > amount || amount > MAX) {
            throw new IllegalArgumentException("범위가 올바르지 않습니다.");
        }
    }
}
