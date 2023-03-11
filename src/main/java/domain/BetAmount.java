package domain;

public class BetAmount {
    public BetAmount(int betAmount) {
        if (betAmount < 1000) {
            throw new IllegalArgumentException("베팅 금액은 최소 1000원 입니다.");
        }
    }
}
