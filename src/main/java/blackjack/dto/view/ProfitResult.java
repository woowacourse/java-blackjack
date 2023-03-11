package blackjack.dto.view;

public class ProfitResult {

    private final String name;
    private final int profitAmount;

    public ProfitResult(final String name, final int profitAmount) {
        this.name = name;
        this.profitAmount = profitAmount;
    }

    public String getName() {
        return name;
    }

    public int getProfitAmount() {
        return profitAmount;
    }
}
