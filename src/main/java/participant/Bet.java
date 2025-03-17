package participant;

public final class Bet {
    private static final int AMOUNT_UNIT = 10000;

    private final int amount;

    public Bet(final int amount) {
//        validate(amount); // TODO Profit을 따로 만들까?
        this.amount = amount;
    }

    private void validate(final int amount) {
        if (amount <= 0 || amount % AMOUNT_UNIT != 0) {
            throw new IllegalArgumentException("[ERROR] 배팅은 10,000원 단위로만 가능합니다.");
        }
    }

    public Bet multiply(double rate) {
        return new Bet((int) (amount * rate));
    }

    @Override
    public String toString() {
        return String.valueOf(amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Bet bet = (Bet) o;
        return amount == bet.amount;
    }

    @Override
    public int hashCode() {
        return amount;
    }
}
