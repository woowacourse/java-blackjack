package model.judgement;

public record Profit(int value) {

    public static final Profit ZERO = new Profit(0);

    public Profit add(Profit other) {
        return new Profit(this.value + other.value);
    }

    public Profit negate() {
        return new Profit(-value);
    }
}
