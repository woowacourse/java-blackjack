package participant;

public record Bet(int amount) {
    private static final int AMOUNT_UNIT = 10000;

    public Bet {
        validate(amount);
    }

    private void validate(final int amount) {
        if (amount <= 0 || amount % AMOUNT_UNIT != 0) {
            throw new IllegalArgumentException("[ERROR] 배팅은 10,000원 단위로만 가능합니다.");
        }
    }

    public int toInt() {
        return amount;
    }

    @Override
    public String toString() {
        return String.valueOf(amount);
    }
}
