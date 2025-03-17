package game;

public class Betting {

    private final int betting;

    public Betting(int money) {
        validatePositive(money);
        this.betting = money;
    }

    private void validatePositive(int money) {
        if (money < 0) {
            throw new IllegalArgumentException("[ERROR] 올바른 베팅 금액을 입력해주세요.");
        }
    }

    public int getBetting() {
        return betting;
    }
}
