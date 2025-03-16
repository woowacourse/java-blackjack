package model.participant;

public final class Bet {
    private static final int amountUnit = 10000;

    private final int amount;

    public Bet(final int amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(final int amount) {
        if (amount <= 0 || amount % amountUnit != 0) {
            throw new IllegalArgumentException("[ERROR] 배팅은 10,000원 단위로만 가능합니다.");
        }
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
