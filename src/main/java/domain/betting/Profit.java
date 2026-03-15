package domain.betting;

public record Profit(
        int amount
){

    public Profit addProfit(Profit other) {
        return new Profit(this.amount + other.amount);
    }

    public Profit reverseProfit() {
        return new Profit(-this.amount);
    }

    public int getProfit() {
        return this.amount;
    }
}
