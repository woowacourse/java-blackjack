package domain.result;

public class ProfitFixture {
    public static Profit amountOf(int amount) {
        return Profit.of(() -> amount, ProfitRate.WIN);
    }
}
