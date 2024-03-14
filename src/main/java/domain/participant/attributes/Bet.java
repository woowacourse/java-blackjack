package domain.participant.attributes;

public record Bet(int amount) {

    public Bet add(final Bet bet) {
        return new Bet(amount + bet.amount);
    }

    public Bet subtract(final Bet bet) {
        return new Bet(amount - bet.amount);
    }

    public Bet multiply(final double multiplier) {
        return new Bet((int) (amount * multiplier));
    }
}
