package bet;

public class Bet {
    private final int amount;

    public Bet(int amount) {
        if (amount > 100_000_000 || amount <= 0) {
            throw new IllegalArgumentException("베팅 금액에 문제가 있습니다.");
        }

        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}
